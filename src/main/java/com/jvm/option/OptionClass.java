package com.jvm.option;

import com.jvm.constant.Constant;
import com.jvm.search.ClassPath;
import com.jvm.search.EntryFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @Description: 命令行操作方法
 * @Author: WindPursuer
 * @Date 2018/12/25 8:12 PM
 */
public class OptionClass {

    private static final Logger logger = LoggerFactory.getLogger(OptionClass.class);

    /**
     * 暂时单例（后续改动）
     */
    private static OptionClass options = new OptionClass();

    private OptionClass(){}

    public static OptionClass getInstance() {
        return options;
    }



    /**
     * @Description: 获取option属性
     * @Author: WindPursuer
     * @Date 2018/12/28 5:04 PM
     */
    public OptionProperty parse(String args[]) {
        logger.info("method start, param :【{}】", args.toString());
        OptionProperty optionProperty = new OptionProperty();
        for (int i = 0; i < args.length; i = i + 2) {
            switch (args[i]) {
                case Constant.VERSION:
                    optionProperty.setVersion(Constant.VERSION_);
                    return optionProperty;
                case Constant.CLASS:
                    optionProperty.setClassName(args[i + 1]);
                    break;
                case Constant.USER_CLASSPATH:
                    optionProperty.setUserPath(args[i + 1]);
                    break;
                case Constant.X_JRE:
                    optionProperty.setJrePath(args[i + 1]);
                    break;
                default:
                    break;
            }
        }
        if (args.length % 2 != 0) {
            optionProperty.setClassName(args[args.length - 1]);
        }
        return optionProperty;
    }


    /**
     * @Description: 解析命令行
     * @Author: WindPursuer
     * @Date 2018/12/25 8:22 PM
     */
    public ClassPath parse(String jreOptions, String userPath) {
        ClassPath classPath = new ClassPath();
        parseBootAndExtClasspath(jreOptions, classPath);
        parseUserClasspath(userPath, classPath);
        return classPath;
    }



    /**
     * @Description: 将命令行解析成classpath
     * @Author: WindPursuer
     * @Date 2018/12/25 8:14 PM
     */
    private void parseBootAndExtClasspath(String jreOption, ClassPath classPath) {
        logger.info("获得bootClass和ExtClass的Entry,jreOption:【{}】", jreOption);
        if (StringUtils.isEmpty(jreOption)) {
            jreOption = getJreDir();
        }
        String jreLibPath = jreOption + "/lib/*";
        classPath.setBootClassEntry(EntryFactory.newEntry(jreLibPath));
        String jreExtPath = jreOption + "/lib/ext/*";
        classPath.setExtClassEntry(EntryFactory.newEntry(jreExtPath));
    }

    /**
     * @Description: 设置用户路径
     * @Author: WindPursuer
     * @Date 2018/12/25 8:19 PM
     */
    private void parseUserClasspath(String userPath, ClassPath classPath){
        logger.info("获得用户Entry,userPath:【{}】", userPath);
        if (StringUtils.isEmpty(userPath)) {
            userPath = ".";
        }
        classPath.setUserClassEntry(EntryFactory.newEntry(userPath));
    }


    /**
     * @Description: 获取jre安装目录
     * @Author: WindPursuer
     * @Date 2018/12/28 4:51 PM
     */
    private static String getJreDir() {
        Properties props=System.getProperties(); //系统属性
        logger.info("Java的安装路径："+ props.getProperty("java.home"));
        return  props.getProperty("java.home");
    }


}
