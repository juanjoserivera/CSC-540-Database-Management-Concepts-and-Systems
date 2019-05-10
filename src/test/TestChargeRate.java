package test;

import be.ChargeRateBe;
import bl.ChargeRateBl;

public class TestChargeRate {

	public static void main(String[] args) {
		ChargeRateBl bl = new ChargeRateBl();

		System.out.println(bl.get(1));
		System.out.println(bl.getAll());

		ChargeRateBe be = new ChargeRateBe();
		be.setId(3);
		be.setType("HIGH");
		be.setCostPerHour(20);

		bl.insert(be);

		System.out.println(bl.getAll());

		bl.delete(3);

		System.out.println(bl.getAll());
	}

}
