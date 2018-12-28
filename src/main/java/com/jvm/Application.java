package com.jvm;

import com.jvm.interpreter.Interpret;
import com.jvm.option.OptionClass;
import com.jvm.option.OptionProperty;
import com.jvm.search.ClassPath;
import com.jvm.search.EntryResult;
import com.jvm.soClassLoader.domain.Method;
import com.jvm.soClassLoader.domain.SoClass;
import com.jvm.soClassLoader.domain.SoClassLoader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Description: 入口，定义命令参数
 * @Author: WindPursuer
 * @Date 2018/11/8 10:40 AM
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    /**
     * @Description: 程序入口
     * @Author: WindPursuer
     * @Date 2018/11/8 10:40 AM
     */
    public static void main(String[] args) {
        /**
         * 后续完善命令行
         * 1、search class
         */
        OptionProperty optionProperty = OptionClass.getInstance().parse(args);
        if (!StringUtils.isEmpty(optionProperty.getVersion())) {
            logger.info("Version:【{}】", optionProperty.getVersion());
            return;
        }

        if (StringUtils.isEmpty(optionProperty.getClassName())) {
            logger.info("参数错误");
            return;
        }
        String jreOption = optionProperty.getJrePath();
        String cpOption = optionProperty.getUserPath();
        String className = optionProperty.getClassName();
        ClassPath classPath = OptionClass.getInstance().parse(jreOption, cpOption);
        EntryResult result = classPath.readClass(className);
        logger.info("Class:【{}】", result.getData());
//      startJVM();
    }

    private static void startJVM(){
        String path = "/Users/wangfa/IdeaProjects/sojvm/sojvm/src/main/java/com/jvm/util//Test.class";
        SoClassLoader loader = new SoClassLoader(path,true);
        //String className = "com/sojvm/Test";
        SoClass mainClass = loader.loadClass(loader.getClassFilePath());
        Method mainMethod = mainClass.getMainMethod();
        if (mainMethod != null){
            Interpret.interpret(mainMethod,true);
        }else {
            System.out.println("空");
        }
    }
}
