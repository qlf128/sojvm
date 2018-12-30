package com.jvm.search;

import com.jvm.option.OptionClass;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @Description: classpath路径
 * @Author: WindPursuer
 * @Date 2018/12/25 8:04 PM
 */
public class ClassPath {

    private static final Logger logger = LoggerFactory.getLogger(ClassPath.class);

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
