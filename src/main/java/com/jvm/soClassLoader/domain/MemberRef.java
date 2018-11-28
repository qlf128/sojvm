package com.jvm.soClassLoader.domain;

import com.jvm.classReader.model.constant.ConstantMemberRefInfo;

/**
 * @author luao
 * @date 2018-11-12 18:39
 */
public class MemberRef extends SymRef{

    private String name;

    private String descriptor;

    public void copyMemberRefInfo(ConstantMemberRefInfo cfMemberInfo){
        this.setName(cfMemberInfo.getName());
        this.setDescriptor(cfMemberInfo.getDescriptor());
        this.setClassName(cfMemberInfo.getClassName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }
}
