package be;

import java.sql.Date;
import be.PartOrderBe;

public class NotificationBe {
	private int id;
	private Date notificationDate;
	private PartOrderBe partOrderBe;
	private String fromName;
	private int delay;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}
	public PartOrderBe getPartOrderBe() {
		return partOrderBe;
	}
	public void setPartOrderBe(PartOrderBe partOrderBe) {
		this.partOrderBe = partOrderBe;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	@Override
	public String toString() {
		return "NotificationBe [id=" + id + ", notificationDate=" + notificationDate + ", partOrderBe=" + partOrderBe.toString()
				+ ", fromName=" + fromName +", delay="+delay+ "]";
	}
	


	

}
