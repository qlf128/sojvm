package com.jvm.nativeLoader;

import com.jvm.soClassLoader.domain.SoClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.util.Properties;

/**
 * @Description 运行本地方法驱动
 * @Author wangshasha
 * @Date 2018/12/4 下午4:51
 * @Version
 */
public class NativeDriver {

    private static Logger logger = LoggerFactory.getLogger(NativeDriver.class);

    private static native void registerNatives();
    static {
        registerNatives();
    }

    public NativeDriver() {
    }


    //加载本地类库
    @CallerSensitive
    public static void nativeLibraryLoad(String libName) {
       NativeLibraryLoad.loadNativeLibrary(NativeReflection.getCallerClass(), libName);

    }









}
