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
    private String bootClassPath;

    /**
     * 扩展类路径
     */
    private String extClassPath;

    /**
     * 用户类路径
     */
    private String userClassPath;

    public ClassPath() {

    }

    public ClassPath(String bootClassPath, String extClassPath, String userClassPath) {
        this.bootClassPath = bootClassPath;
        this.extClassPath = extClassPath;
        this.userClassPath = userClassPath;
    }



    public String getBootClassPath() {
        return bootClassPath;
    }

    public void setBootClassPath(String bootClassPath) {
        this.bootClassPath = bootClassPath;
    }

    public String getExtClassPath() {
        return extClassPath;
    }

    public void setExtClassPath(String extClassPath) {
        this.extClassPath = extClassPath;
    }

    public String getUserClassPath() {
        return userClassPath;
    }

    public void setUserClassPath(String userClassPath) {
        this.userClassPath = userClassPath;
    }
}
