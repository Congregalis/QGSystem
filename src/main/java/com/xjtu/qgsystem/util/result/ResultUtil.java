package com.xjtu.qgsystem.util.result;

public class ResultUtil {
    public static <T> Result<T> defineSuccess(Integer code, T data) {
        Result result = new Result<>();
        result.setCode(code);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(T data) {
        Result result = new Result();
        result.setCode(ResultEnum.FAIL.getCode());
        result.setMsg(ResultEnum.FAIL.getMsg());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> noPermission(T data) {
        Result result = new Result();
        result.setCode(ResultEnum.NoPermission.getCode());
        result.setMsg(ResultEnum.NoPermission.getMsg());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> defineFail(int code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> define(int code, String msg, T data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
