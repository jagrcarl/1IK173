import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A class for creating TimeRegStore stubs.
 */
public class TimeRegStoreStub extends TimeRegStore {

	/**
	 * Helper container
	 */
	List<TimeRegEntity> entries = null;

	/**
	 * Create a TimeRegStore instance.
	 */
	public TimeRegStoreStub() {
		entries = new ArrayList<TimeRegEntity>();
	}

	/**
	 * Helper for adding a TimeRegEntity
	 */
	public void addTimeRegEntity(String userId, String projId, float hoursWorked, boolean isChargeable, Date date) {
		TimeRegEntity tre = new TimeRegEntity(userId, projId, hoursWorked, isChargeable, date);
		entries.add(tre);
	}

	/**
	 * A stub implementation of the base class' getMonthlyRegs(). It returns an
	 * array of pre-configured TimeRegEntity objects.
	 */
	@Override
	public TimeRegEntity[] getMonthlyRegs(String userId, int year, int month) {
		TimeRegEntity[] arr = new TimeRegEntity[entries.size()];
		return entries.toArray(arr);
	}

}
