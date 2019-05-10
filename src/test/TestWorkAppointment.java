package test;

import java.time.LocalDate;
import java.util.List;

import be.WorkAppointmentBe;
import bl.BasicServiceBl;
import bl.WorkAppointmentBl;
import util.TimeSlot;

public class TestWorkAppointment {

	public static void main(String[] args) {
		WorkAppointmentBl bl = new WorkAppointmentBl();

		WorkAppointmentBe be = bl.get(1);
		System.out.println(be);
		
		BasicServiceBl basicServiceBl = new BasicServiceBl();
		LocalDate date = basicServiceBl.getLastServiceDate(18, "DEL-8888");
		System.out.println(date);
		date = basicServiceBl.getLastServiceDate(6 , "DEL-8888");
		System.out.println(date);
//		WorkAppointmentBe be = new WorkAppointmentBe();
//		be.setId(3);
//		be.setStatus("PENDING");
//		be.setStartDate(Timestamp.from(Instant.now()));
//		be.setEndDate(Timestamp.from(Instant.now()));
//		be.setTotalCost(999);
//
//		bl.insert(be);
//		System.out.println(bl.getAll());
//		bl.delete(3);
//		System.out.println(bl.getAll());
	}
	
	private static void print(List<WorkAppointmentBe> ws) {
		for (WorkAppointmentBe w : ws) {
			System.out.println(w);
		}
	}
	
	private static void printTS(List<TimeSlot> ws) {
		for (TimeSlot w : ws) {
			System.out.println(w);
		}
	}
}
