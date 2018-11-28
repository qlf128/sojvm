package com.jvm.soClassLoader.domain;


import com.jvm.classReader.MemberInfo;
import com.jvm.classReader.model.attribute.CodeAttribute;

/**
 * @author luao
 * @date 2018-11-12 16:07
 */
public class Method extends ClassMember {

    private int maxStack;

    private int maxLocals;

    private byte[] code;


    public static Method[] newMethods(SoClass soClass, MemberInfo[] cfMethods){
        if(cfMethods==null||cfMethods.length<=0){
            return null;
        }
        Method[] methods = new Method[cfMethods.length];
        for (int i = 0; i < cfMethods.length; i++) {
            methods[i].copyClassMember(cfMethods[i]);
            methods[i].setSoClass(soClass);
        }
        return methods;
    }

    public void copyAttributes(MemberInfo cfMethod){
        CodeAttribute codeAttr = cfMethod.getCodeAttribute();
        this.maxStack = codeAttr.getMaxStack();
        this.maxLocals = codeAttr.getMaxLocals();
        this.code = codeAttr.getCode();
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }
}
