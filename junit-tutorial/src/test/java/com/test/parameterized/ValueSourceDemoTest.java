package com.test.parameterized;

import com.test.MathUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ValueSourceDemoTest {

    //@Test
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 6, 8, 10})
    void isEvenTest(int number) {
        MathUtils mathUtils = new MathUtils();
        assertTrue(mathUtils.isEven(number));
        /*assertTrue(mathUtils.isEven(3));
        assertTrue(mathUtils.isEven(4));
        assertTrue(mathUtils.isEven(6));
        assertTrue(mathUtils.isEven(8));
        assertTrue(mathUtils.isEven(10));*/
    }
    @ParameterizedTest
    @ValueSource(strings = {"Hello","Junit","Parameterized","Test"})
    void valueSourceTest(String param) {
        assertNotNull(param);
    }
}
