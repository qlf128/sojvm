package com.jvm;

import com.jvm.option.OptionClass;
import com.jvm.option.OptionProperty;
import com.jvm.search.ClassPath;
import com.jvm.soClassLoader.domain.SoClass;
import com.jvm.soClassLoader.domain.SoClassLoader;

public class Start {

    public void start(String[] args){
        Cmd cmd = new Cmd().parseCmd(args);

        //测试数据
        cmd.setClazz("com/test/Test");
        cmd.setCpOption("/Users/qlf/code/sojvm/");//文件夹路径
        cmd.setXjreOption("");//jdk安装目录

        if(cmd.isVersionFlag()){
            System.out.println("version 0.0.1");
        }else if(cmd.isHelpFlag()  || cmd.getClazz() == ""){
            cmd.printUsage();
        }else{
            new JVM(cmd).start();
        }
    }

    public static void main(String[] args){
        new Start().start(args);
    }
}
