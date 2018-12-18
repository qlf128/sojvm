package com.jvm.nativeLoader;

import com.jvm.soClassLoader.domain.SoClass;
import sun.reflect.CallerSensitive;

/**
 * @Description 反射
 * @Author wangshasha
 * @Date 2018/12/18 下午12:15
 * @Version
 */
public class NativeReflection {

    public NativeReflection() {
    }

    @CallerSensitive
    public static native SoClass getCallerClass();

    /** @deprecated */
    @Deprecated
    public static native SoClass getCallerClass(int var0);






}
