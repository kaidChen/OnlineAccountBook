package com.toys.ACB.constant;

public enum DBConstant {
    BILL_SOURCE_CASH(0, "现金支出"),
    BILL_SOURCE_LOAN(1, "借款支出"),
    TYPE_KIND_OUTCOME(0, "支出"),
    TYPE_KIND_INCOME(1, "收入"),
    TYPE_STATUS_INVISIBLE(0, "不可见"),
    TYPE_STATUS_VISIBLE(1, "可见"),
    USER_STATUE_LOCKED(0, "用户被锁定"),
    USER_STATUS_UNLOCKED(1, "用户可用");

    private final Integer code;
    private final String msg;

    DBConstant(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
