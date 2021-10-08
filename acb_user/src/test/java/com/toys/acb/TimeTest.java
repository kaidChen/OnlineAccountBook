package com.toys.acb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeTest {
    @Test
    public void test1() {
        LocalDate date = LocalDate.now();
        LocalDate firstday = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(firstday);
        System.out.println(lastDay);
    }
}
