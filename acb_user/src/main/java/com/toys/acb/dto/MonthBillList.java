package com.toys.acb.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class MonthBillList {
    private Integer year;
    private Integer month;
    private BigDecimal sum;
    private List<DailyBillList> list = new ArrayList<>();

    public static MonthBillList newFromList(Integer year, Integer month, List<BillDto> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        MonthBillList monthBillList = new MonthBillList();
        DailyBillList dailyBillList = new DailyBillList();
        monthBillList.getList().add(dailyBillList);
        LocalDate datePtr = null;
        for (BillDto dto : list) {
            if (datePtr == null) {
                datePtr = dto.getCreatedAt();
            } else if (!datePtr.equals(dto.getCreatedAt())) {
                datePtr = dto.getCreatedAt();
                dailyBillList = new DailyBillList();
                monthBillList.getList().add(dailyBillList);
            }

            dailyBillList.getList().add(dto);
        }

        for (DailyBillList daily : monthBillList.getList()) {
            daily.parseFromList();
            monthBillList.setSum(monthBillList.getSum().add(daily.getSum()));
        }

        monthBillList.setYear(year);
        monthBillList.setMonth(month);

        return monthBillList;
    }
}

@Data
class DailyBillList {
    private String date;
    private BigDecimal sum;
    private List<BillDto> list;

    public void parseFromList() {
        if (this.list == null || list.isEmpty()) {
            return;
        }

        this.date = list.get(1).getCreatedAt().toString();
        for (BillDto dto : this.list) {
            this.sum = this.sum.add(dto.getCost());
        }
    }
}