package bl;

import be.DistributorBe;
import dao.DistributorDao;

/**
 * @author Juan Jose Rivera
 *
 */
public class DistributorBl {
	
	public DistributorBe getDistributorByPartId(int partId) {
		DistributorDao distributorDao =new DistributorDao();
		DistributorBe distributorBe;
		
		distributorBe=distributorDao.getDistributorByPartId(partId);
		return distributorBe;

	}
}
