package Test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XMathTest {

    @Test
    void testAdd() {
        assertEquals(2, XMath.add(1, 1));
    }
    @Test
    void testDiv() {
        assertEquals(0, XMath.div(1, 2));
    }
}