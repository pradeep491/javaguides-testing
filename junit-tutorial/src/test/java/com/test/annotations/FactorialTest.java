package com.test.annotations;

import com.test.Factorial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {

    @Test
    protected void factorialTest() {
        Factorial factorial = new Factorial();
        int actualResult = factorial.factorial(3);
        assertEquals(6, actualResult);
    }
}