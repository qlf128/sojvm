package com.jvm.soClassLoader.domain;

import com.jvm.classReader.model.constant.ConstantInterfaceMethodRefInfo;

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
}
