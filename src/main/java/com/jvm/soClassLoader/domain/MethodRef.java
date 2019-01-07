package com.jvm.soClassLoader.domain;

import com.jvm.classReader.model.constant.ConstantMethodRefInfo;
import com.jvm.soClassLoader.util.MethodLookupUtil;
import com.sun.source.tree.MemberReferenceTree;

/**
 * @author luao
 * @date 2018-11-12 18:30
 */
public class MethodRef extends MemberRef {
    private Method method;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public MethodRef newMethodRef(ConstantPool constantPool, ConstantMethodRefInfo refInfo){
        MethodRef ref = new MethodRef();
        ref.setCp(constantPool);
        ref.copyMemberRefInfo(refInfo);
        return ref;
    }

    public Method resolvedMethod(){
        if (this.method == null){
            resolveMethodRef();
        }
        return this.method;
    }

    private void resolveMethodRef(){
        ConstantPool constantPool = super.getCp();
        SoClass d = constantPool.getSoClass();
        SoClass c = super.resolvedClass();
        if (c.isInterface()){
            throw new RuntimeException("IncompatibleClassChangeError");
        }
        Method method = lookupMethod(c, super.getName(),super.getDescriptor());
        if (method == null){
            throw new RuntimeException("NoSuchMethodError");
        }
        if (!method.isAccessibleTo(d)){
            throw new RuntimeException("IllegalAccessError");
        }
        setMethod(method);
    }

    private Method lookupMethod(SoClass soClass, String name, String descriptor){
        Method method = MethodLookupUtil.lookupMethodInClass(soClass,name,descriptor);
        if (method == null){
            method = MethodLookupUtil.lookupMethodInInterfaces(soClass.getInterfaces(),name,descriptor);
        }
        return method;
    }

}
