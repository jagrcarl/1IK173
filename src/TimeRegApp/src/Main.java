import java.util.Date;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Using TimeRegStoreStub1.
		 */
		TimeRegEngine tre = new TimeRegEngine(new TimeRegStoreStub_Bad());
		System.out.println(tre.getChargeability("lars", 2019, 1));
		
		/*
		 * Using TimeRegStoreStub2.
		 */
		
		TimeRegStoreStub stub = new TimeRegStoreStub();
		TimeRegEngine tre2 = new TimeRegEngine(stub);
		stub.addTimeRegEntity("Lars", "p1", 4, true, new Date(2019,1,1));
		stub.addTimeRegEntity("Lars", "p1", 8, true, new Date(2019,1,1));
		
		System.out.println(tre2.getChargeability("lars", 2019, 1));
	}

}
