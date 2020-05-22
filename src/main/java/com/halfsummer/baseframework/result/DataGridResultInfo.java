package com.halfsummer.baseframework.result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BestClever
 * @title: DataGridResultInfo
 * @projectName springboot_study
 * @description: layui 表格返回值
 * @date 2020-02-18 13:52
 */
public class DataGridResultInfo {
    private int code = 0 ;
    private String msg = "";
    private Long count;
    private List data = new ArrayList();

    public DataGridResultInfo(Long count, List data) {
        this.count = count;
        this.data = data;
    }

    public DataGridResultInfo() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
