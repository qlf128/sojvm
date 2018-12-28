package com.jvm.option;

/**
 * @Description: 命令行选项属性
 * @Author: WindPursuer
 * @Date 2018/12/28 5:01 PM
 */
public class OptionProperty {

    /**
     * jre 路径
     */
    private String jrePath;

    /**
     * 用户路径
     */
    private String userPath;

    /**
     * 类名路径
     */
    private String className;

    /**
     * 版本
     */
    private String version;


    public OptionProperty() {
    }

    public String getJrePath() {
        return jrePath;
    }

    public void setJrePath(String jrePath) {
        this.jrePath = jrePath;
    }

    public String getUserPath() {
        return userPath;
    }

    public void setUserPath(String userPath) {
        this.userPath = userPath;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
