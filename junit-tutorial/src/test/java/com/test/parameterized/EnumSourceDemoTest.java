package com.test.parameterized;

import com.test.Days;
import com.test.MathUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;

public class EnumSourceDemoTest {

    @ParameterizedTest
    @EnumSource(value = Days.class,
            names = {
                    "MONDAY",
                    "TUESDAY",
                    "WEDNESDAY",
                    "THURSDAY",
                    "FRIDAY",
                    "SUNDAY",
                    "SATURDAY"
            })
    void isWeekDayTest(Days day) {
        MathUtils mathUtils = new MathUtils();
        assertTrue(mathUtils.isWeekDay(day));
    }
}
