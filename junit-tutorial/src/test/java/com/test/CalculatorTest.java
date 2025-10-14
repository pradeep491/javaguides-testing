package com.test;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int actualResult = calculator.add(10, 30);
        assertEquals(40, actualResult);
    }
}
