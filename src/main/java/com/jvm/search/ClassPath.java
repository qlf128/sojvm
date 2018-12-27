package com.jvm.search;

/**
 * @Description: classpath路径
 * @Author: WindPursuer
 * @Date 2018/12/25 8:04 PM
 */
public class ClassPath {

    /**
     * 启动类路径
     */
    private Entry bootClassEntry;

    /**
     * 扩展类路径
     */
    private Entry extClassEntry;

    /**
     * 用户类路径
     */
    private Entry userClassEntry;

    public ClassPath() {

    }

    public ClassPath(Entry bootClassEntry, Entry extClassEntry, Entry userClassEntry) {
        this.bootClassEntry = bootClassEntry;
        this.extClassEntry = extClassEntry;
        this.userClassEntry = userClassEntry;
    }


    /**
     * @Description: 读取Class
     * @Author: WindPursuer
     * @Date 2018/12/27 8:24 PM
     */
    public EntryResult readClass(String className) {
        className = className + ".class";
        EntryResult entryResult = this.bootClassEntry.readClass(className);
        if (entryResult.isSuccess()) {
            return entryResult;
        }
        entryResult = this.extClassEntry.readClass(className);
        if (entryResult.isSuccess()) {
            return entryResult;
        }
        entryResult = this.userClassEntry.readClass(className);
        if (entryResult.isSuccess()) {
            return entryResult;
        }
        return new EntryResult(false);
        
    }


    public Entry getBootClassEntry() {
        return bootClassEntry;
    }

    public void setBootClassEntry(Entry bootClassEntry) {
        this.bootClassEntry = bootClassEntry;
    }

    public Entry getExtClassEntry() {
        return extClassEntry;
    }

    public void setExtClassEntry(Entry extClassEntry) {
        this.extClassEntry = extClassEntry;
    }

    public Entry getUserClassEntry() {
        return userClassEntry;
    }

    public void setUserClassEntry(Entry userClassEntry) {
        this.userClassEntry = userClassEntry;
    }
}
