package com.test.annotations;

import com.test.Calculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AfterEachDemoTest {

    private Calculator calculator;

    @BeforeEach
    void setUp(){
        calculator = new Calculator();
        System.out.println("setUp() invoked...!");
    }
    @Test
    @DisplayName("Addition of the Two numbers")
    void addTest() {
        int sum = calculator.add(2, 3);
        assertEquals(5, sum);
        System.out.println("addTest() calling...!");
    }
    @Test
    @DisplayName("substraction of the Two numbers")
    void substractTest() {
        int result = calculator.substract(2, 3);
        assertEquals(-1, result);
        System.out.println("substractTest() calling...!");
    }
    //Emoji chars
    @Test
    @DisplayName("Multiplication of the Two numbers $&* \uD83D\uDE00")
    void multiplyTest() {
        int result = calculator.multiply(2, 3);
        assertEquals(6, result);
        System.out.println("multiplyTest() calling...!");
    }
    @Test
    @DisplayName("division of the Two numbers $&* \uD83D\uDE00")
    void devideTest() {
        int result = calculator.devide(6, 3);
        assertEquals(2, result);
        System.out.println("devideTest() calling...!");
    }

    @AfterEach
    void tearDown(){
        System.out.println("executed after the test case");
        calculator = null;
    }

}
