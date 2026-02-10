package com.test.annotations;


import com.test.Calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class RepeatedTestDemoTest {

    @BeforeEach
    void setUp(){
        System.out.println("setUp() method is calling");
    }

    @RepeatedTest(value = 5,name = RepeatedTest.LONG_DISPLAY_NAME)
    @DisplayName("Test addition of two numbers repeatedly")
    public void addTest() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3));
        System.out.println("addTest method is calling...!");
    }

    @AfterEach
    void tearDown(){
        System.out.println("Teardown method is calling");
    }

    @BeforeAll
    static void setUpBeforeClass(){
        System.out.println("setUpBeforeClass method is calling...!");
    }

    @AfterAll
    static void tearDownAfterClass(){
        System.out.println("tearDownAfterClass method is calling...!");
    }
}
