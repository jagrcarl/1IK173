package ZString;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ZStringTest {

	
	@Test
	void testZStringCtor() {
		
		ZString cut = new ZString();
		assertNull(cut.data);
	}

	@Test
	void testZStringCharArray() {
		char[] strArr = new char[] {1,2,3};
		
		ZString cut = new ZString(strArr);
		
		/*
		 *  We could simply test so that the lengths are equal,
		 *  but that is not enough.
		 */
		//assertEquals(3, cut.data.length);
		
		assertArrayEquals(strArr, cut.data);
	}
	
	@Test
	void testAppendAString(){
		char[] strArr1 = new char[] {'a', 'b', 'c', 1};
		char[] strArr2 = new char[] {'e', 'f'};
		char[] expectedArr = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
		
		ZString cut = new ZString(strArr1);
		ZString str = new ZString(strArr2);
		
		cut.append(str);
		
		assertArrayEquals(expectedArr, cut.data);
		
	}
	
	@Test
	void testAppendAnEmptyString(){
		char[] strArr1 = new char[] {'a', 'b', 'c', 'd'};
		
		ZString cut = new ZString(strArr1);
		ZString str = new ZString();
		
		cut.append(str);
		
		assertArrayEquals(strArr1, cut.data);
		
	}
		

}
