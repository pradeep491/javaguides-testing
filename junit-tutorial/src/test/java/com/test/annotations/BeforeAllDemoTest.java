package com.test.annotations;

import com.test.Calculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeforeAllDemoTest {

    private static Calculator calculator;

    /*@BeforeEach
    void setUp(){
        calculator = new Calculator();
        System.out.println("setUp() invoked...!");
    }*/
    @BeforeAll
    static void setUpBeforeClass(){
        calculator = new Calculator();
        System.out.println("setUp before class method is calling...!");
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

}
