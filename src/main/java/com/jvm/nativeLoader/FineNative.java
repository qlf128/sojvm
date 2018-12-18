package com.jvm.nativeLoader;

import com.jvm.soClassLoader.domain.SoClass;

import java.util.List;

/**
 * @Description 查找本地方法
 * @Author wangshasha
 * @Date 2018/12/18 上午10:55
 * @Version
 */
public class FineNative {

    // 查找本地方法, 类加载时触发
    public static long findNative(SoClass soClass, String name) {
        NativeLibraryLoad nativeLibraryLoad = new NativeLibraryLoad(name, soClass);
        List<NativeLibraryLoad> libs =
                soClass != null ? nativeLibraryLoad.nativeLibaryLoaded : nativeLibraryLoad.sysNativeLiararyLoad;
        for (NativeLibraryLoad lib : libs) {
            long entry = lib.find(name);
            if (entry != 0)
                return entry;
        }
        return 0;
    }
}
