import java.util.Date;

/**
 * POD for a time registration.
 * @author lars.karlsson
 *
 */
public class TimeRegEntity {
	private String userId;
	private Date date;
	private String projectId;
	private float hoursWorked;
	private boolean isChargeable;
	
	public TimeRegEntity(String userId, String projId, 
			 float hoursWorked, boolean isChargeable, Date date ) {
		this.setUserId(userId);
		this.setProjectId(projId);
		this.setHoursWorked(hoursWorked);
		this.setChargeable(isChargeable);
		this.setDate(date);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public float getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(float hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public boolean isChargeable() {
		return isChargeable;
	}

	public void setChargeable(boolean isChargeable) {
		this.isChargeable = isChargeable;
	}
}
