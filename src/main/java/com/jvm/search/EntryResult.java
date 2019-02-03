package com.jvm.search;

/**
 * @Description: entry的ReadClass方法返回结果
 * @Author: WindPursuer
 * @Date 2018/12/25 11:36 PM
 */
public class EntryResult {

    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * class数据
     */
    private byte[] data;

    /**
     * entry
     */
    private Entry entry;

    /**
     * 错误信息
     */
    private String error;

    public EntryResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public EntryResult(boolean isSuccess, byte[] data, Entry entry, String error) {

        this.data = data;
        this.entry = entry;
        this.error = error;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
