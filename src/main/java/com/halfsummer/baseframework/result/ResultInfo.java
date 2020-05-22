package com.halfsummer.baseframework.result;


/**
 * ResponseData:
 *
 */

public class ResultInfo {

    /**
     * 统一返回码
     */
    public Integer code;

    /**
     * 统一错误消息
     */
    public String msg;

    /**
     * 结果对象
     */
    public Object data;

    public ResultInfo() {
    }

    public ResultInfo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultInfo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
