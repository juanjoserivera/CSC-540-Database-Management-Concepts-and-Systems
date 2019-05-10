package bl;

import java.util.ArrayList;

import be.ChargeRateBe;
import dao.ChargeRateDao;

public class ChargeRateBl {

	public ChargeRateBe get(int id) {
		ChargeRateDao dao = new ChargeRateDao();
		return dao.get(id);
	}

	public ArrayList<ChargeRateBe> getAll() {
		ChargeRateDao dao = new ChargeRateDao();
		return dao.getAll();
	}

	public boolean insert(ChargeRateBe be) {
		ChargeRateDao dao = new ChargeRateDao();
		return dao.insert(be);
	}

	public boolean delete(int id) {
		ChargeRateDao dao = new ChargeRateDao();
		return dao.delete(id);
	}

}
