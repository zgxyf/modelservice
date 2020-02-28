package com.fengyu.modelservice.vo;

/**
 * 统一返回
 */
public class ResponseResult {
    /**
     * 状态码
     */
    private String code;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 返回数据.
     */
    private Object data;

    public ResponseResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * error
     * @param code 错误码
     * @param errorMessage 错误信息
     * @return
     */
    public static ResponseResult error(String code, String errorMessage) {
        return new ResponseResult(code, errorMessage, null);
    }

    /**
     * success
     * @param data 错误码
     * @param message 错误信息
     * @return
     */
    public static ResponseResult success(String message, Object data) {
        return new ResponseResult("200", message, data);
    }

    public static ResponseResult success() {
        return new ResponseResult("200", null, null);
    }
    public static ResponseResult success(Object data) {
        return new ResponseResult("200", null, data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
