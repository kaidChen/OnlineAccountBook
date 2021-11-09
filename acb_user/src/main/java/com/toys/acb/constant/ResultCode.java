package com.toys.acb.constant;

/*
200：成功
1001-1999：参数错误
2001-2999：用户错误
3001-3999：接口异常
4001-4999：业务错误
5001-5999：运行时错误
 */

public enum ResultCode {
    SUCCESS(200, "成功"),
    COMMON_FAIL(999, "失败"),

    // 参数错误
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    // 用户错误
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USED_BY_OTHER(2009, "账号已超时或在另一台机器登录，被迫下线"),
    USER_SESSION_INVALID(2010, "会话无效"),


    // 业务错误
    NO_PERMISSION(4001, "没有权限"),
    TOKEN_EXPIRED(4002, "token已过期"),
    TOKEN_INVALID(4003, "token无效"),
    TOKEN_ERROR(4004, "token错误"),

    // 运行异常
    ARITHMETIC_EXCEPTION(9001, "算数异常"),
    NULL_POINTER_EXCEPTION(9002, "空指针异常"),
    ARRAY_INDEX_OUTOFBOUNDS_EXCEPTION(9003, "数组越界"),
    SYSTEM_EXCEPTION(9004, "系统异常"),

    DB_DATA_NOT_EXISTS(10001, "数据不存在"),
    ;

    private final Integer code;
    private final String message;
    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
