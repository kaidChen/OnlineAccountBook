package com.toys.acb.dto;

import com.toys.acb.constant.DbCode;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class MonthBillList {
    private Integer year;
    private Integer month;
    private BigDecimal income;
    private BigDecimal outcome;
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
                dailyBillList.setDate(datePtr.toString());
            } else if (!datePtr.equals(dto.getCreatedAt())) {
                datePtr = dto.getCreatedAt();
                dailyBillList = new DailyBillList();
                dailyBillList.setDate(datePtr.toString());
                monthBillList.getList().add(dailyBillList);
            }

            dailyBillList.getList().add(dto);
        }

        for (DailyBillList daily : monthBillList.getList()) {
            daily.parseFromList();
            monthBillList.setIncome(monthBillList.getIncome().add(daily.getIncome()));
            monthBillList.setOutcome(monthBillList.getOutcome().add(daily.getOutcome()));
        }

        monthBillList.setYear(year);
        monthBillList.setMonth(month);

        return monthBillList;
    }
}

@Data
class DailyBillList {
    private String date;
    private BigDecimal income;
    private BigDecimal outcome;
    private List<BillDto> list = new ArrayList<>();

    public void parseFromList() {
        if (this.list == null || list.isEmpty()) {
            return;
        }

        this.date = list.get(1).getCreatedAt().toString();
        for (BillDto dto : this.list) {
            if (dto.getBillType().getKind().equals(DbCode.BillTypeKindIncome)) {
                this.income = this.income.add(dto.getCost());
            } else {
                this.outcome = this.outcome.add(dto.getCost());
            }
        }
    }
}