import java.lang.*;

public class TimeRegEngine {
	TimeRegStore store = null;
	final double HoursPerMonth = 160.0; 
	
	public TimeRegEngine(TimeRegStore trs) {
		store = trs;
	}
	
	public double getChargeability(String userId, int year, int month) {
		TimeRegEntity[] entities = store.getMonthlyRegs(userId, year, month);
		double sumChargebleHours = 0;
		for (TimeRegEntity tre : entities) {
			sumChargebleHours += tre.isChargeable() ? tre.getHoursWorked() : 0;
		}
		return sumChargebleHours / HoursPerMonth;
	}
	
}
