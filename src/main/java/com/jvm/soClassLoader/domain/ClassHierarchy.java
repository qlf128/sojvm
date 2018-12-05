package com.jvm.soClassLoader.domain;

/**
 * go语言可以将方法到外部文件中，通过receiver声明指明属于哪个对象
 * java里只好接收两个入参实现调用者传递
 * TODO 注意此类static方法形参传递的次序问题，待验证
 */
public class ClassHierarchy {

    public static boolean isAssignableFrom(SoClass self, SoClass other){
        if (self == other){
            return true;
        }
        //调用类是接口
        if (! self.isInterface()){
            return isSubClassOf(self,other);
        } else {
            return isImplements(self,other);
        }
    }

    public static boolean isSubClassOf(SoClass self, SoClass other){
        for (SoClass c = self.getSuperClass(); c != null; c = c.getSuperClass()){
            //在jvm层面判断是否是子类，不能用instanceof
            if (c == other){
                return true;
            }
        }
        return false;
    }

    public static boolean isImplements(SoClass self, SoClass iface){
        for (SoClass c = self; c != null; c = c.getSuperClass()){
            for (SoClass soClass : c.getInterfaces()){
                if (soClass == iface || isSubInterfaceOf(soClass, iface)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断self是否是iface的子接口,即self不断获取父接口，看能否等于iface,使用了递归
     * @param self
     * @param iface
     * @return
     */
    public static boolean isSubInterfaceOf(SoClass self, SoClass iface){
        for (SoClass superInterface : self.getInterfaces()){
            if (superInterface == iface || isSubInterfaceOf(superInterface,iface)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断self是否是other的父类就是判断other是否是self的子类
     * @param self
     * @param other
     * @return
     */
    public static boolean isSuperClassOf(SoClass self, SoClass other){
        return isSubClassOf(other,self);
    }
}
