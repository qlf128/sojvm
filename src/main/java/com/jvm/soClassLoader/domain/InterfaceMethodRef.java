package com.jvm.soClassLoader.domain;

import com.jvm.classReader.model.constant.ConstantInterfaceMethodRefInfo;
import com.jvm.soClassLoader.util.MethodLookupUtil;

/**
 * @author luao
 * @date 2018-11-12 20:15
 */
public class InterfaceMethodRef extends MemberRef{
    private Method method;

    public InterfaceMethodRef newInterfaceMethodRef(ConstantPool cp, ConstantInterfaceMethodRefInfo refInfo){
        InterfaceMethodRef ref = new InterfaceMethodRef();
        ref.setCp(this.getCp());
        ref.copyMemberRefInfo(refInfo);
        return ref;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Method resolvedInterfaceMethod(){
        if (this.method == null){
            resolveInterfaceMethodRef();
        }
        return this.method;
    }

    private void resolveInterfaceMethodRef(){
       SoClass d = super.getCp().getSoClass();
       SoClass c = super.resolvedClass();
       if (!c.isInterface()){
           throw new RuntimeException("IncompatibleClassChangeError");
       }
       Method method = lookupInterfaceMethod(c,super.getName(),super.getDescriptor());
       if (method == null){
           throw new RuntimeException("NoSuchMethodError");
       }
       if (!method.isAccessibleTo(d)){
           throw new RuntimeException("IllegalAccessError");
       }
       this.method = method;
    }
    //不同于MethodRef.java, InterfaceMethodRef.java中寻找方法只能在当前接口和超接口中，MethodRef.java还可以在类中找
    private Method lookupInterfaceMethod(SoClass iface, String name, String descriptor) {
        for (Method method : iface.getMethods()){
            if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)){
                return method;
            }
        }
        return MethodLookupUtil.lookupMethodInInterfaces(iface.getInterfaces(),name,descriptor);
    }
}
