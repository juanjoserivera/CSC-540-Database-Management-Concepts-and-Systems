package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import be.EmployeeBe;

public class UI {
	
	public static void displayServiceDuration(int durationMinutes) {
		System.out.format("Service Duration: %s\n",
				LocalTime.of(durationMinutes / 60, durationMinutes % 60).format(DateFormatUtil.timeFormatter));
	}
	
	public static void displaySumOfMaintenanceServiceDurations(int durationMinutesSum, LocalDate date) {
		System.out.format("Sum of Maintenance Services Durations: %s (on %s)\n",
				LocalTime.of(durationMinutesSum / 60, durationMinutesSum % 60).format(DateFormatUtil.timeFormatter),
				date.format(DateFormatUtil.dateFormatter));
	}

	public static void displaySumOfRepairServiceDurations(int durationMinutesSum, LocalDate date) {
		System.out.format("Sum of Repair Services Durations: %s (on %s)\n",
				LocalTime.of(durationMinutesSum / 60, durationMinutesSum % 60).format(DateFormatUtil.timeFormatter),
				date.format(DateFormatUtil.dateFormatter));
	}

	public static void displayServiceCost(double cost) {
		System.out.format("Service Cost: $%s\n", cost);
	}

	public static void displayAvailableDateTimes(List<LocalDateTime> availableDateTimes) {
		System.out.println("Available Dates:");
		for (LocalDateTime dateTime : availableDateTimes) {
			System.out.format("    * %s\n", dateTime.format(DateFormatUtil.dateTimeFormatter));
		}
	}

	public static void displayMechanic(EmployeeBe mechanic) {
		System.out.format("Mechanic: %s\n", mechanic.getName());
	}

	public static void displayPickDateTimeOptions(List<LocalDateTime> availableDateTimes) {
		System.out.println("Pick the Date (1-2):");
		for (int i = 0; i < availableDateTimes.size(); i++) {
			System.out.format("%s. %s\n", (i + 1), availableDateTimes.get(i).format(DateFormatUtil.dateTimeFormatter));
		}
	}

}
