package com.tuniu.ord.vo.common;

import java.util.List;

public class ExternalResponseObj<T> {
    private boolean success;
    private String msg;
    private Integer errorCode;
    private Data<T> data;

    public static class Data<T> {
        private int count;
        private List<T> rows;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<T> getRows() {
            return rows;
        }

        public void setRows(List<T> rows) {
            this.rows = rows;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Data<T> getData() {
        return data;
    }

    public void setData(Data<T> data) {
        this.data = data;
    }

}
