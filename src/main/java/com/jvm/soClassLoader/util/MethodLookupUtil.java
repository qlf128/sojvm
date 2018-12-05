package com.jvm.soClassLoader.util;

import com.jvm.soClassLoader.domain.Method;
import com.jvm.soClassLoader.domain.SoClass;

/**
 * 方法查找工具类
 */
public class MethodLookupUtil {
    //从父类中查找方法Method
    public static Method lookupMethodInClass(SoClass soClass, String name, String descriptor){
        for (SoClass spring = soClass; spring != null; spring = spring.getSuperClass()){
            for (Method m : spring.getMethods()){
                if (m.getName().equals(name) && m.getDescriptor().equals(descriptor)){
                    return m;
                }
            }
        }
        return null;
    }
    //从接口列表中查找方法Method
    public static Method lookupMethodInInterfaces(SoClass[] ifaces, String name, String descriptor){
        for (SoClass iface : ifaces){
            for (Method m : iface.getMethods()){
                if (m.getName().equals(name) && m.getDescriptor().equals(descriptor)){
                    return m;
                }
            }
            Method method = lookupMethodInInterfaces(iface.getInterfaces(),name,descriptor);
            if (method != null){
                return method;
            }
        }
        return null;
    }
}
