package bl;
import java.util.HashMap;
import java.util.Map;

import dao.UpdateInventoryDao;
public class UpdateInventoryBl {
	public boolean updateInventory() {
		UpdateInventoryDao uDao = new UpdateInventoryDao();
		HashMap<Integer,Integer> valuesParts;
		valuesParts = uDao.getPartstoUpdate();
		for(Map.Entry<Integer,Integer> entry: valuesParts.entrySet()) {
			uDao.removeFromBookedParts(entry.getKey(), entry.getValue());
		}
		uDao.updateWorkStatus();
		return true;
	}
}
