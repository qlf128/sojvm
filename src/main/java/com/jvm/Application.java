package com.jvm;

import com.jvm.interpreter.Interpret;
import com.jvm.option.OptionClass;
import com.jvm.search.*;
import com.jvm.soClassLoader.domain.Method;
import com.jvm.soClassLoader.domain.SoClass;
import com.jvm.soClassLoader.domain.SoClassLoader;

import java.io.InputStream;
import java.util.List;

/**
 * @Description: 入口，定义命令参数
 * @Author: WindPursuer
 * @Date 2018/11/8 10:40 AM
 */
public class Application {
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
        //List<String> pathList = FileSearchImpl.getInstance().searchClass("path");
        //for (String path : pathList) {
        //    InputStream inputStream = ReadClass.readClass(path);
        //}
        String jreOption = "";
        String cpOption = "";
        String className = "";
        ClassPath classPath = OptionClass.getInstance().parse(jreOption, cpOption);
        EntryResult result = classPath.readClass(className);
        startJVM();
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
