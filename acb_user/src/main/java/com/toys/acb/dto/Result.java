package com.toys.acb.dto;

import com.toys.acb.constant.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data;

    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.COMMON_FAIL.getCode());
        result.setMessage(ResultCode.COMMON_FAIL.getMessage());
        return result;
    }

    public static Result error(ResultCode resultCode) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    public Result success(Boolean success) {
        setSuccess(success);
        return this;
    }

    public Result message(String message) {
        setMessage(message);
        return this;
    }

    public Result code(Integer code) {
        setCode(code);
        return this;
    }

    public Result data(Map<String, Object> map) {
        setData(map);
        return this;
    }

    public Result data(String name, Object obj) {
        if (getData() == null) {
            setData(new HashMap<>());
        }
        getData().put(name, obj);
        return this;
    }
}
