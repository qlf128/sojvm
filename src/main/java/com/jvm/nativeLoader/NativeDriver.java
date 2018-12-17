package com.jvm.nativeLoader;

import com.jvm.soClassLoader.domain.SoClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

/**
 * @Description 运行本地方法驱动
 * @Author wangshasha
 * @Date 2018/12/4 下午4:51
 * @Version
 */
public class NativeDriver {

    private static Logger logger = LoggerFactory.getLogger(NativeDriver.class);


    public static void nativeLoad(String libName) {

        SoClass soClass = SoClass.getCallerClass();

        NativeLibraryLoad nativeLibraryLoad = new NativeLibraryLoad();



    }



}
