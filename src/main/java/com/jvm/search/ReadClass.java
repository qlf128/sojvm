package com.jvm.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * @Description: 根拒绝对路径获取流
 * @Author: WindPursuer
 * @Date 2018/11/8 11:41 AM
 */
public class ReadClass {

    private static Logger logger = LoggerFactory.getLogger(ReadClass.class);

    /**
     * @Description: 获取流
     * @Author: WindPursuer
     * @Date 2018/11/8 12:10
     */
    public static InputStream readClass(String path) {
        try {
            return new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static InputStream readClass(ClassPath classPath, String className) {

        try {
            return new FileInputStream(new File(className));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
}
