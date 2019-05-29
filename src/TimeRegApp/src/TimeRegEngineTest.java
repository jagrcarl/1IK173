import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

class TimeRegEngineTest {

	@Test
	void testGetChargeability() {
		// Setup
		TimeRegStoreStub stub = new TimeRegStoreStub();
		TimeRegEngine cut = new TimeRegEngine(stub);

		// Run
		stub.addTimeRegEntity("john", "p1", 2, true, new Date(2019, 1, 1));
		stub.addTimeRegEntity("john", "p1", 2, false, new Date(2019, 1, 2));
		stub.addTimeRegEntity("john", "p1", 2, true, new Date(2019, 1, 3));

		// Run and Assert
		assertEquals(cut.getChargeability("john", 2019, 1), 0.025);

	}

	@Test
	void testGetChargeability_UsingMockito() {
		// Setup
		TimeRegStore trs = mock(TimeRegStore.class);
		TimeRegEngine cut = new TimeRegEngine(trs);

		when(trs.getMonthlyRegs("john", 2019, 1))
				.thenReturn(new TimeRegEntity[] { new TimeRegEntity("john", "p1", 2, true, new Date(2019, 1, 1)),
						new TimeRegEntity("john", "p1", 2, false, new Date(2019, 1, 2)),
						new TimeRegEntity("john", "p1", 2, true, new Date(2019, 1, 3)), });

		// Run and Assert
		assertEquals(cut.getChargeability("john", 2019, 1), 0.025);

	}
	
	
	@Test
	void testGetChargeability_UsingMockito2() {
		// Setup
		TimeRegStore trs = mock(TimeRegStore.class);
		TimeRegEngine cut = new TimeRegEngine(trs);

		when(trs.getMonthlyRegs("john", 2019, 1))
				.thenReturn(new TimeRegEntity[] { new TimeRegEntity("john", "p1", 2, true, new Date(2019, 1, 1)),
						new TimeRegEntity("john", "p1", 2, false, new Date(2019, 1, 2)),
						new TimeRegEntity("john", "p1", 2, true, new Date(2019, 1, 3)), });

		// Run and Assert
		assertEquals(cut.getChargeability("smith", 2019, 1), 0.025);

	}
	
	@Test
	void testGetChargeability_UsingMockito3() {
		// Setup
		TimeRegStore trs = mock(TimeRegStore.class);
		TimeRegEngine cut = new TimeRegEngine(trs);

		when(trs.getMonthlyRegs("john", 2019, 1))
				.thenReturn(new TimeRegEntity[] { new TimeRegEntity("john", "p1", 2, true, new Date(2019, 1, 1)),
						new TimeRegEntity("john", "p1", 2, false, new Date(2019, 1, 2)),
						new TimeRegEntity("john", "p1", 2, true, new Date(2019, 1, 3)), });

		// Recording
		cut.getChargeability("john", 2019, 1);
		
		// Verify
		verify(trs).getMonthlyRegs("john", 2019, 1);

	}

	@Test
	void testGetChargeability_InOrderVerify() {
		List singleMock = mock(List.class);

		 singleMock.add("was added first");
		 singleMock.add("was added second");

		 InOrder inOrder = inOrder(singleMock);

		 /*
		  * Check so that add("was added first") was called
		  * before add("was added second")
		  */
		 inOrder.verify(singleMock).add("was added first");
		 inOrder.verify(singleMock).add("was added second");

	}
	
	
	
}
