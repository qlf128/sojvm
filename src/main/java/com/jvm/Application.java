package com.jvm;

import com.jvm.search.FileSearchImpl;
import com.jvm.search.ReadClass;
import com.jvm.search.Search;

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
        List<String> pathList = FileSearchImpl.getInstance().searchClass("path");
        for (String path : pathList) {
            InputStream inputStream = ReadClass.readClass(path);
        }


    }
}
