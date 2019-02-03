package com.jvm.constant;

/**
 * @Description: 常量类
 * @Author: WindPursuer
 * @Date 2018/12/25 11:34 PM
 */
public class Constant {

    /** 路径分隔符 */
    public static final String pathListSeparator = System.getProperties().get("path.separator").toString();


    /** 版本参数 */
    public static final String VERSION_ = "sojvm 1.0.0";

    /** 版本参数 */
    public static final String VERSION = "-v";

    /** jre参数 */
    public static final String X_JRE = "-jre";

    /** 用户路径参数 */
    public static final String USER_CLASSPATH = "-cp";

    /** 类名参数 */
    public static final String CLASS = "-n";




}
