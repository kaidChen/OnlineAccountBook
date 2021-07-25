package com.toys.acb.constant;

public enum DbCode {
    BILL_SOURCE_CASH(0, "现金支出"),
    BILL_SOURCE_LOAN(1, "借款支出"),
    TYPE_KIND_OUTCOME(0, "消费"),
    TYPE_KIND_INCOME(1, "收入"),
    TYPE_KIND_SAVING(2, "储蓄"),
    TYPE_KIND_MANAGING(3, "理财"),
    TYPE_KIND_LENDING(4, "借出"),
    TYPE_STATUS_INVISIBLE(0, "不可见"),
    TYPE_STATUS_VISIBLE(1, "可见"),
    USER_STATUE_LOCKED(0, "用户被锁定"),
    USER_STATUS_UNLOCKED(1, "用户可用"),
    ROLE_ADMIN(1, "管理员角色"),
    ROLE_USER(2, "用户角色")
    ;

    private final Integer code;
    private final String msg;

    DbCode(Integer code, String msg) {
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
