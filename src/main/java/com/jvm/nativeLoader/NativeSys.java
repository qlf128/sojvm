package com.jvm.nativeLoader;

import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * @Description
 * @Author wangshasha
 * @Date 2018/12/18 下午3:06
 * @Version
 */
public class NativeSys {

    private static Properties props;

    private static native void registerNatives();
    static {
        registerNatives();
    }

    private NativeSys() {
    }

    private static native Properties initProperties(Properties props);

    public static Properties getProps() {
        return props;
    }

    public static void setProps(Properties props) {

        if(props == null) {
            props = new Properties();
            initProperties(props);

        }
        NativeSys.props = props;
    }

    public static String getProperty(String key) {
        if(StringUtils.isEmpty(key)) {
            throw new RuntimeException();
        }
        return props.getProperty(key);
    }

    public static String setProperty(String key, String value) {
        if(StringUtils.isEmpty(key)) {
            throw new RuntimeException();
        }

        return (String) props.setProperty(key, value);

    }


    public static native long currentTimeMillis();

    public static native void arraycopy(Object src,  int  srcPos,
                                        Object dest, int destPos,
                                        int length);

    public static native int identityHashCode(Object x);




}
