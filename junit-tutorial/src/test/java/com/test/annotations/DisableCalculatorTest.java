package com.test.annotations;


import com.test.Calculator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisableCalculatorTest {

    @Disabled("Disabled until the bug is fixed")
    @Test
    @DisplayName("Addition of the Two numbers")
    void addTest() {
        Calculator calculator = new Calculator();
        int sum = calculator.add(2, 3);
        assertEquals(5, sum);
    }
    @Test
    @DisplayName("substraction of the Two numbers")
    void substractTest() {
        Calculator calculator = new Calculator();
        int result = calculator.substract(2, 3);
        assertEquals(-1, result);
    }
    //Emoji chars
    @Test
    @DisplayName("Multiplication of the Two numbers $&* \uD83D\uDE00")
    void multiplyTest() {
        Calculator calculator = new Calculator();
        int result = calculator.multiply(2, 3);
        assertEquals(6, result);
    }
}
