package com.toys.acb;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println(now.getYear());
        System.out.println(now.getMonthValue());
        System.out.println(now.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(now.with(TemporalAdjusters.lastDayOfMonth()));

        LocalDate date = LocalDate.of(2021, 11, 5);
        System.out.println(date);

        System.out.println(date.isEqual(now));

        BigDecimal dec1 = new BigDecimal("1.123");
        BigDecimal dec2 = new BigDecimal("2.000");
        BigDecimal add = dec1.add(dec2);
        System.out.println(dec1);
        System.out.println(dec2);
        System.out.println(add);
    }
}
