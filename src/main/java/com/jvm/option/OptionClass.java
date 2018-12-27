package com.jvm.option;

import com.jvm.search.ClassPath;
import com.jvm.search.EntryFactory;
import com.jvm.search.FileSearchImpl;
import com.jvm.search.Search;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        String jreLibPath = jreOption + "/lib/*";
        classPath.setBootClassEntry(EntryFactory.newEntry(jreLibPath));
        String jreExtPath = jreOption + "/lib/ext/*";
        classPath.setBootClassEntry(EntryFactory.newEntry(jreExtPath));
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
}
