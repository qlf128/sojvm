package com.jvm.nativeLoader;

import com.jvm.runTimeDateArea.model.SoArrayObject;
import com.jvm.soClassLoader.domain.SoClass;
import com.jvm.soClassLoader.domain.SoClassLoader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.Reflection;

import java.io.File;
import java.nio.file.Path;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;

/**
     * Description  用于封装被加载过的本地类
     * @Author wangshasha
     * @Date 2018/12/3 下午4:15
     */
public class NativeLibraryLoad {

    private  static Logger logger = LoggerFactory.getLogger(com.jvm.nativeLoader.NativeLibraryLoad.class);

    //本地类库的名称
    private String nativeLibraryName;
    // 被加载本地类库类
    private SoClass nativeClass;
    //jni 版本号, 唯一
    private int jniVersion;
    //本地类库的地址
    private static String[] sysPaths;
    //系统类库的地址
    private static String[] userPaths;
    //当前类库的名称
    String name;
    //当前类
    SoClass soClass;
    //当前本地库是否处理
    Boolean handle;

    //系统加载本地库
    public  static List<NativeLibraryLoad> sysNativeLiararyLoad = new ArrayList<>();
    //被加载过的本地库
    public  List<NativeLibraryLoad> nativeLibaryLoaded = new ArrayList<>();
    //以静态变量的形式保存被加载过的本地库名称
    public static List<String> nativeLibraryNames = new ArrayList<>();
    //保存在stack
    public  static Stack<NativeLibraryLoad> nativeLibraryStack = new Stack();

    /**
     * 1.查找，加载 和 卸载本地类库
     * 主要与操作系统交互
     */
    native void load(String name);
    native long find(String name);
    native void unLoad();

    public NativeLibraryLoad() {
    }

    public NativeLibraryLoad(String nativeLibraryName, SoClass nativeClass) {
            this.nativeLibraryName = nativeLibraryName;
            this.nativeClass = nativeClass;
        }
    //加载类库
    public static void loadNativeLibrary(SoClass nativeClass, String nativeName) {

        logger.info("method start, param -> nativeClass:【{}】, nativeName:【{}】", nativeClass, nativeName);
        /**
         * 1.初始化路径
         * */
        if(sysPaths == null) {
            sysPaths = initPaths("sun.boot.library.path");
        }
        userPaths = initPaths("java.library.path");

        /**
         *2.首先从class load 读取
         * */
        SoClassLoader loader = nativeClass == null ? null : nativeClass.getSoClassLoader();

        if(loader != null) {

            String libName = loader.getClassFilePath();
            File file = new File(libName);
            //加载类库
            if(realLodeLibrary(nativeClass, file)) {
                return;
            }
        }
        /**
         * 3.从sysPaths读取
         * */
        for(int i=0; i < sysPaths.length; i++ ) {
            File libFile = new File(sysPaths[i], System.mapLibraryName(nativeName));
            if(realLodeLibrary(nativeClass, libFile)) {
                return;
            }
        }

        /**
         * 4.从userPaths读取
         * */
        for(int i=0; i < userPaths.length; i++) {
            // mapLibraryName将nativeName映射为库文件的文件名
            File libFile = new File(userPaths[i], System.mapLibraryName(nativeName));
            if(realLodeLibrary(nativeClass, libFile)) {
                return;
            }
        }
    }

   // 初始化本地库的文件路径

    private static String[] initPaths(String propertyName) {

            logger.info("method start, param -> path", propertyName);


            String path = NativeSys.getProperty(propertyName);

            int len = path.length();

            if(path == null || path.length() == 0) {
                throw new RuntimeException(propertyName + "is null");
            }

            //系统默认路径分隔符
            String ps = File.pathSeparator;


            int i, j, n;
            // Count the separators in the path
            i = path.indexOf(ps);
            n = 0;
            while (i >= 0) {
                n++;
                i = path.indexOf(ps, i + 1);
            }

            // allocate the array of paths - n :'s = n + 1 path elements
            String[] paths = new String[n + 1];

            // Fill the array with paths from the ldpath
            n = i = 0;
            j = path.indexOf(ps);
            while (j >= 0) {
                if (j - i > 0) {
                    paths[n++] = path.substring(i, j);
                } else if (j - i == 0) {
                    paths[n++] = ".";
                }
                i = j + 1;
                j = path.indexOf(ps, i);
            }
            paths[n] = path.substring(i, len);
            return paths;

    }

    private static native String findBuiltinLib(String name);

    private static Boolean realLodeLibrary(SoClass nativeClass, File libFile) {
        logger.info("method start, param -> nativeClass:【{}】, libFile:【{}】", nativeClass, libFile );

        //根据fileLibName在本地类库查找
        String libraryName = findBuiltinLib(libFile.getName());

        if(!StringUtils.isEmpty(libraryName)) {
            //action
            PrivilegedAction privilegedAction = new PrivilegedAction<Object>() {
                public Object run() {
                    //return libFile.exists() ? Boolean.TRUE : null;
                    return null;
                }};
            boolean exists = AccessController.doPrivileged(privilegedAction) != null;
            String name = null;
            if(exists) {
                try{
                    //将路径名转换成绝对路径名，这与调用 getAbsolutePath() 方法的效果一样，然后用与系统相关的方式将它映射到其惟一路径名
                    name = libFile.getCanonicalPath();
                } catch (Exception e) {
                    logger.info(e.getMessage());
                    return false;
                }
            }

            /** 维护一个本地库列表
             * 1.系统类去维护一个本地库列表，保存由系统加载的本地库名称。
             * 2.每个类加载实例保存由它加载过的本地库名称
             * 3.所有被加载过的本地库都以静态变量的形式保存起来
             * 4.本地库被保存在stack中
             * */
            SoClassLoader classLoader = nativeClass == null ? null : nativeClass.getSoClassLoader();
            List<NativeLibraryLoad> nativeLibraryLoads = new ArrayList<>();
            if(classLoader != null) {
                NativeLibraryLoad nativeLibraryLoad = new NativeLibraryLoad(name, nativeClass);
                nativeLibraryLoads = nativeLibraryLoad.nativeLibaryLoaded;
            } else {
                nativeLibraryLoads = sysNativeLiararyLoad;
            }

            for(NativeLibraryLoad nativeLibraryLoad : nativeLibraryLoads) {
                if(nativeLibraryLoad.name.equals(name)) {
                    return true;
                }
            }

            if(nativeLibraryNames.contains(name)) {
                throw new Error("该类库已被其他类加载");
            }

           for(NativeLibraryLoad nativeLibraryLoad : nativeLibraryStack) {
               if(nativeLibraryLoad.name.equals(name)) {
                   if(classLoader != nativeLibraryLoad.soClass.getSoClassLoader()) {
                       throw new Error("该类库已被其他类加载");
                   } else {
                       return true;
                   }
               }
           }
            NativeLibraryLoad nativeLibraryLoad = new NativeLibraryLoad(name, nativeClass);
            nativeLibraryStack.push(nativeLibraryLoad);
            nativeLibraryLoad.load(name);
            nativeLibraryStack.pop();

            if(nativeLibraryLoad.handle) {
               nativeLibraryNames.add(name);
               nativeLibraryLoads.add(nativeLibraryLoad);
                return true;
            }
        }
        return false;
    }








}
