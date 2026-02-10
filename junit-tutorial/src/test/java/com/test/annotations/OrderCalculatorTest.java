package com.test.annotations;

import com.test.Calculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderCalculatorTest {

    @Order(1)
    @Test
    void testAdd() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3));
    }
    @Order(2)
    @Test
    void testSubstract() {
        Calculator calculator = new Calculator();
        assertEquals(1, calculator.substract(3, 2));
    }
    @Order(3)
    @Test
    void testMultiply() {
        Calculator calculator = new Calculator();
        assertEquals(6, calculator.multiply(3, 2));
    }
    @Order(4)
    @Test
    void testDevide() {
        Calculator calculator = new Calculator();
        assertEquals(2, calculator.devide(4, 2));
    }
}
