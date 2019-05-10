package test;

//import static org.junit.Assert.assertArrayEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import org.junit.jupiter.api.Test;

import util.ServiceScheduler;
import util.TimeSlot;

class ServiceSchedulerTest {
//
//	@Test
//	void testNoBusySlots() {
//		List<TimeSlot> busyTimeSlots = Arrays.asList();
//		List<TimeSlot> expectedTimeSlots = Arrays.asList(new TimeSlot(LocalTime.of(8, 0), 660));
//		List<TimeSlot> freeTimeSlots = ServiceScheduler.getFreeTimeSlots(busyTimeSlots);
//		assertArrayEquals(expectedTimeSlots.toArray(), freeTimeSlots.toArray());
//	}
//
//	@Test
//	void testOneBusySlotAtStart() {
//		List<TimeSlot> busyTimeSlots = Arrays.asList(new TimeSlot(LocalTime.of(8, 0), 90));
//		List<TimeSlot> expectedTimeSlots = Arrays.asList(new TimeSlot(LocalTime.of(9, 30), 570));
//		List<TimeSlot> freeTimeSlots = ServiceScheduler.getFreeTimeSlots(busyTimeSlots);
//		assertArrayEquals(expectedTimeSlots.toArray(), freeTimeSlots.toArray());
//	}
//
//	@Test
//	void testOneBusySlotAtMiddle() {
//		List<TimeSlot> busyTimeSlots = Arrays.asList(new TimeSlot(LocalTime.of(9, 30), 90));
//		List<TimeSlot> expectedTimeSlots = Arrays.asList(new TimeSlot(LocalTime.of(8, 0), 90),
//				new TimeSlot(LocalTime.of(11, 0), 480));
//		List<TimeSlot> freeTimeSlots = ServiceScheduler.getFreeTimeSlots(busyTimeSlots);
//		assertArrayEquals(expectedTimeSlots.toArray(), freeTimeSlots.toArray());
//	}
//
//	@Test
//	void testOneBusySlotAtEnd() {
//		List<TimeSlot> busyTimeSlots = Arrays.asList(new TimeSlot(LocalTime.of(18, 0), 60));
//		TimeSlot[] expectedTimeSlots = new TimeSlot[] { new TimeSlot(LocalTime.of(8, 0), 600) };
//		List<TimeSlot> freeTimeSlots = ServiceScheduler.getFreeTimeSlots(busyTimeSlots);
//		assertArrayEquals(expectedTimeSlots, freeTimeSlots.toArray());
//	}
//
//	@Test
//	void testOneBusySlotFilled() {
//		List<TimeSlot> busyTimeSlots = Arrays.asList(new TimeSlot(LocalTime.of(8, 0), 660));
//		TimeSlot[] expectedTimeSlots = new TimeSlot[] {};
//		List<TimeSlot> freeTimeSlots = ServiceScheduler.getFreeTimeSlots(busyTimeSlots);
//		assertArrayEquals(expectedTimeSlots, freeTimeSlots.toArray());
//	}
//
//	// 8:00 1:45 9:45 30
//	// 10:15 1:15 11:30 90
//	// 13:00 2:30 15:30 60
//	// 16:30 2:30 19
//	@Test
//	void testMultipleBusySlotsInBetween() {
//		List<TimeSlot> busyTimeSlots = Arrays.asList(new TimeSlot(LocalTime.of(9, 45), 30),
//				new TimeSlot(LocalTime.of(11, 30), 90), new TimeSlot(LocalTime.of(15, 30), 60));
//		TimeSlot[] expectedTimeSlots = new TimeSlot[] { new TimeSlot(LocalTime.of(8, 0), 60 + 45),
//				new TimeSlot(LocalTime.of(10, 15), 60 + 15), new TimeSlot(LocalTime.of(13, 0), 2 * 60 + 30),
//				new TimeSlot(LocalTime.of(16, 30), 2 * 60 + 30) };
//		List<TimeSlot> freeTimeSlots = ServiceScheduler.getFreeTimeSlots(busyTimeSlots);
//		assertArrayEquals(expectedTimeSlots, freeTimeSlots.toArray());
//	}
//
//	// 8:00 90 | 9:30 4
//	// 14:30 60 | 15:30 1
//	// 16:30 2:30
//	@Test
//	void testMultipleBusySlotsOnlyMiddle() {
//		List<TimeSlot> busyTimeSlots = Arrays.asList(new TimeSlot(LocalTime.of(8, 0), 90),
//				new TimeSlot(LocalTime.of(14, 30), 60), new TimeSlot(LocalTime.of(16, 30), 2 * 60 + 30));
//		TimeSlot[] expectedTimeSlots = new TimeSlot[] { new TimeSlot(LocalTime.of(9, 30), 5 * 60),
//				new TimeSlot(LocalTime.of(15, 30), 60) };
//		List<TimeSlot> freeTimeSlots = ServiceScheduler.getFreeTimeSlots(busyTimeSlots);
//		assertArrayEquals(expectedTimeSlots, freeTimeSlots.toArray());
//	}
//	
//	// 8:00 105 | 9:45 30
//	// 10:15 75 | 11:30 90
//	// 13:00 150 | 15:30 300
//	@Test
//	void testMultipleBusySlotsOnlyMiddleExcludingLast() {
//		List<TimeSlot> busyTimeSlots = Arrays.asList(new TimeSlot(LocalTime.of(9, 45), 30),
//				new TimeSlot(LocalTime.of(11, 30), 90), new TimeSlot(LocalTime.of(15, 30), 300));
//		TimeSlot[] expectedTimeSlots = new TimeSlot[] { new TimeSlot(LocalTime.of(8, 0), 105),
//				new TimeSlot(LocalTime.of(10, 15), 75), new TimeSlot(LocalTime.of(13, 0), 150) };
//		List<TimeSlot> freeTimeSlots = ServiceScheduler.getFreeTimeSlots(busyTimeSlots);
//		assertArrayEquals(expectedTimeSlots, freeTimeSlots.toArray());
//	}
//	
//	// 8:00 90 | 9:30 4
//	// 16:30 2:30
//	@Test
//	void testGetAvailableTimesDuringFreeDay() {
//		long duration = 75;
//		List<TimeSlot> busyTimeSlots = new ArrayList<TimeSlot>();
//		LocalTime[] expectedTimes = new LocalTime[] { LocalTime.of(8, 0), LocalTime.of(9, 15) };
//		List<LocalTime> availableTimes = ServiceScheduler.getAvailableTimes(duration, busyTimeSlots);
//		assertArrayEquals(expectedTimes, availableTimes.toArray());
//	}
//	
//
//	// 9:45 30 | 8:00 1:45
//	// 11:30 90 | 10:15 1:15 
//	// 15:30 60 | 13:00 2:30 
//	// 19 | 16:30 2:30 
//	@Test
//	void testGetAvailableTimes() {
//		long duration = 60 + 45;
//		List<TimeSlot> busyTimeSlots = Arrays.asList(new TimeSlot(LocalTime.of(9, 45), 30),
//				new TimeSlot(LocalTime.of(11, 30), 90), new TimeSlot(LocalTime.of(15, 30), 60));
//		LocalTime[] expectedTimes = new LocalTime[] { LocalTime.of(8, 0), LocalTime.of(13, 0) };
//		List<LocalTime> availableTimes = ServiceScheduler.getAvailableTimes(duration, busyTimeSlots);
//		assertArrayEquals(expectedTimes, availableTimes.toArray());
//	}
//	
//	// 9:45 30 | 8:00 1:45
//	// 11:30 90 | 10:15 1:15 
//	// 15:30 600 | 13:00 2:30 
//	// 19 | 16:30 2:30 
//	@Test
//	void testGetAvailableTimes2() {
//		long duration = 60 + 46;
//		List<TimeSlot> busyTimeSlots = Arrays.asList(new TimeSlot(LocalTime.of(9, 45), 30),
//				new TimeSlot(LocalTime.of(11, 30), 90), new TimeSlot(LocalTime.of(15, 30), 300));
//		LocalTime[] expectedTimes = new LocalTime[] { LocalTime.of(13, 0) };
//		List<LocalTime> availableTimes = ServiceScheduler.getAvailableTimes(duration, busyTimeSlots);
//		assertArrayEquals(expectedTimes, availableTimes.toArray());
//	}
}
