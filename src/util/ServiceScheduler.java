package util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.DisplayInventoryBe;
import be.DisplayOrderBe;
import be.PartNumberBe;
import bl.PartOrderBl;
import bl.ServiceCenterInventoryBl;
import bl.WorkAppointmentBl;
import dao.MaintenanceServiceDao;
import dao.PartOrderDao;
import dao.RepairServiceDao;

public class ServiceScheduler {
	private static final LocalTime startTime = LocalTime.of(8, 0);
	private static final LocalTime thresholdTime = LocalTime.of(19, 0);

	private static WorkAppointmentBl workAppointmentBl = new WorkAppointmentBl();
	private static ServiceCenterInventoryBl serviceCenterInventoryBl = new ServiceCenterInventoryBl();
	private static PartOrderDao partOrderDao = new PartOrderDao();
	private static PartOrderBl partOrderBl = new PartOrderBl();

	private static RepairServiceDao repairServiceDao = new RepairServiceDao();
	private static MaintenanceServiceDao maintenanceServiceDao = new MaintenanceServiceDao();

	public static boolean validateEnoughParts(int serviceCenterId, List<PartNumberBe> partsForService) {
		List<DisplayInventoryBe> partsInInventory = serviceCenterInventoryBl.displayInventory(serviceCenterId);

		Map<Integer, Integer> idToQtyOfInventory = new HashMap<Integer, Integer>();

		for (DisplayInventoryBe be : partsInInventory) {
			idToQtyOfInventory.put(be.getPartId(), be.getQuantity());
		}

		for (PartNumberBe part : partsForService) {
			int partId = part.getPartBe().getId();
			int expectedQty = part.getNum();
			int inventoryQty = idToQtyOfInventory.get(partId);

			if (expectedQty <= inventoryQty) {
				return true;
			} else {
				ArrayList<DisplayOrderBe> orders = partOrderDao.getPendingByPartIdAndServiceCenterId(partId,
						serviceCenterId);

				if (orders.size() == 0) {
					partOrderBl.insetPartOrderFromPartId(partId, expectedQty);
					// todo expected date + 1
					System.out.println("Please try again on ...");
					return false;
				}

				LocalDate lastOrderDate = null;
				int orderedQty = 0;

				for (DisplayOrderBe order : orders) {
					orderedQty += order.getQty();
					LocalDate orderDate = order.getExpectedDeliveryDate().toLocalDate();

					if (lastOrderDate == null || orderDate.isAfter(lastOrderDate)) {
						lastOrderDate = orderDate;
					}

					if (expectedQty > orderedQty + inventoryQty) {
						int qtyToOrder = partOrderBl.getMinOrder(partId, serviceCenterId,
								expectedQty - (orderedQty + inventoryQty));
						partOrderBl.insetPartOrderFromPartId(partId, qtyToOrder);
					}
				}

				System.out.println(orderedQty);
			}
		}

		return false;
	}

	public static List<LocalDateTime> getAvailableDateTimesForMaintenanceService(int mechanicId, int durationMinutes,
			LocalDate date, int serviceCenterId) {
		List<LocalDateTime> availableDateTimes = new ArrayList<LocalDateTime>();

		for (int i = 0; availableDateTimes.size() < 2 && i < 50; i++, date = date.plusDays(1)) {
			int durationMinutesSum = maintenanceServiceDao.getDurationMinutesSum(date);

			UI.displaySumOfMaintenanceServiceDurations(durationMinutesSum, date);

			if ((durationMinutesSum + durationMinutes) > 330) {
				continue;
			}

			List<TimeSlot> busyTimeSlots = workAppointmentBl.getTimeSlots(serviceCenterId, date, mechanicId);

			List<LocalTime> availableTimesOfTheDay = ServiceScheduler.getAvailableTimes(durationMinutes, busyTimeSlots);

			for (LocalTime t : availableTimesOfTheDay) {
				availableDateTimes.add(LocalDateTime.of(date, t));
			}
		}

		return availableDateTimes;
	}

	public static List<LocalDateTime> getAvailableDateTimesForRepairService(int mechanicId, int durationMinutes,
			LocalDate date, int serviceCenterId) {
		List<LocalDateTime> availableDateTimes = new ArrayList<LocalDateTime>();

		for (int i = 0; availableDateTimes.size() < 2 && i < 50; i++, date = date.plusDays(1)) {
			int durationMinutesSum = repairServiceDao.getDurationMinutesSum(date);

			UI.displaySumOfRepairServiceDurations(durationMinutesSum, date);

			if ((durationMinutesSum + durationMinutes) > 330) {
				continue;
			}

			List<TimeSlot> busyTimeSlots = workAppointmentBl.getTimeSlots(serviceCenterId, date, mechanicId);

			List<LocalTime> availableTimesOfTheDay = ServiceScheduler.getAvailableTimes(durationMinutes, busyTimeSlots);

			for (LocalTime t : availableTimesOfTheDay) {
				availableDateTimes.add(LocalDateTime.of(date, t));
			}
		}

		return availableDateTimes;
	}

	public static List<LocalTime> getAvailableTimes(long durationMinutes, List<TimeSlot> busySlots) {
		busySlots = new ArrayList<TimeSlot>(busySlots);
		List<LocalTime> availableTimes = new ArrayList<LocalTime>();

		TimeSlot availableTimeSlot = getAvailableTimeSlot(durationMinutes, busySlots);

		if (availableTimeSlot == null) {
			return availableTimes;
		}

		availableTimes.add(availableTimeSlot.getStartTime());

		busySlots.add(availableTimeSlot);
		Collections.sort(busySlots);

		availableTimeSlot = getAvailableTimeSlot(durationMinutes, busySlots);

		if (availableTimeSlot != null) {
			availableTimes.add(availableTimeSlot.getStartTime());
		}
		return availableTimes;
	}

	private static TimeSlot getAvailableTimeSlot(long durationMinutes, List<TimeSlot> busySlots) {
		List<TimeSlot> freeSlots = getFreeTimeSlots(busySlots);
		for (TimeSlot freeTimeSlot : freeSlots) {
			if (freeTimeSlot.canFit(durationMinutes)) {
				return new TimeSlot(freeTimeSlot.getStartTime(), durationMinutes);
			}
		}
		return null;
	}

	public static List<TimeSlot> getFreeTimeSlots(List<TimeSlot> busySlots) {
		List<TimeSlot> freeSlots = new ArrayList<TimeSlot>();

		if (busySlots.isEmpty()) {
			freeSlots.add(new TimeSlot(startTime, 660));
		} else {
			TimeSlot firstBusySlot = busySlots.get(0);

			if (startTime.isBefore(firstBusySlot.getStartTime())) {
				long durationMinutes = Duration.between(startTime, firstBusySlot.getStartTime()).toMinutes();
				freeSlots.add(new TimeSlot(startTime, durationMinutes));
			}

			for (int i = 0; i < busySlots.size() - 1; i++) {
				TimeSlot curSlot = busySlots.get(i);
				TimeSlot nextSlot = busySlots.get(i + 1);
				long durationMinutes = Duration.between(curSlot.getEndTime(), nextSlot.getStartTime()).toMinutes();
				freeSlots.add(new TimeSlot(curSlot.getEndTime(), durationMinutes));
			}

			TimeSlot lastBusySlot = busySlots.get(busySlots.size() - 1);

			long durationMinutes = Duration.between(lastBusySlot.getEndTime(), thresholdTime).toMinutes();
			if (durationMinutes > 0) {
				freeSlots.add(new TimeSlot(lastBusySlot.getEndTime(), durationMinutes));
			}
		}

		return freeSlots;
	}
}
