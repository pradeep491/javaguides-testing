package com.test.annotations;

import com.test.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NestedCalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Nested
    class AdditionTests {

        @Test
        public void testAddPositiveNumbers() {
            assertEquals(7, calculator.add(3, 4));
        }

        @Test
        public void testAddPositiveAndNegativeNumbers() {
            assertEquals(1, calculator.add(4, -3));
        }

        @Test
        public void testAddNegativeNumbers() {
            assertEquals(-7, calculator.add(-4, -3));
        }

    }

    @Nested
    class SubstractionTests {
        @Test
        public void testSubstractPositiveNumbers() {
            assertEquals(7, calculator.substract(11, 4));
        }

        @Test
        public void testSubstractPositiveAndNegativeNumbers() {
            assertEquals(7, calculator.substract(4, -3));
        }

        @Test
        public void testSubstractNegativeNumbers() {
            assertEquals(-1, calculator.substract(-4, -3));
        }

        @Nested
        class EdgeCases {
            @Test
            public void testSubstractZero() {
                assertEquals(2, calculator.substract(2, 0));
            }
            @Test
            public void testSubstractSelf() {
                assertEquals(0, calculator.substract(2, 2));
            }
        }
    }

    @Nested
    class MultiplicationTests {
        @Test
        public void testMultiplyPositiveNumbers() {
            assertEquals(44, calculator.multiply(11, 4));
        }

        @Test
        public void testMultiplyPositiveAndNegativeNumbers() {
            assertEquals(-12, calculator.multiply(4, -3));
        }

        @Test
        public void testMultiplyNegativeNumbers() {
            assertEquals(12, calculator.multiply(-4, -3));
        }
    }

    @Nested
    class DivisionTests {
        @Test
        public void testDevidePositiveNumbers() {
            assertEquals(3, calculator.devide(12, 4));
        }

        @Test
        public void testDevidePositiveAndNegativeNumbers() {
            assertEquals(-4, calculator.devide(-44, 11));
        }

        @Test
        public void testMultiplyNegativeNumbers() {
            assertEquals(-4, calculator.devide(-12, 3));
        }
    }
}
