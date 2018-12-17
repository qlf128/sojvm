package com.jvm.soClassLoader.domain;

import com.jvm.classReader.MemberInfo;
import com.jvm.soClassLoader.constants.AccessFlagConstant;

/**
 * @author luao
 * @date 2018-11-12 15:18
 */
public class ClassMember {
    private int accessFlags;

    private String name;

    private String descriptor;

    private SoClass soClass;

    public void copyClassMember(MemberInfo memberInfo){
        this.accessFlags = memberInfo.getAccessFlags();
        this.name = memberInfo.getName();
        this.descriptor = memberInfo.getDescriptor();
    }

    public ClassMember(){};

    /**
     * 这一个'类成员'是否可以被d类访问
     * 字段访问规则：
     * 1.如果字段是public，则任何类都可以访问。
     * 2.如果字段是protected，则只有子类和同一个包下的类可以访问。
     * 3.如果字段有默认访问权限（非public，非protected，也非privated），则只有同一个包下的类可以访问。
     * 4.否则，字段是private，只有声明这个字段的类才能访问。
     * @param d
     * @return
     */
    public boolean isAccessibleTo(SoClass d){
        if(this.isPublic()){
            return true;
        }
        SoClass c = this.soClass;
        if(this.isProtected()){
           return  d==c || d.isSubClassOf(c) || c.getPackageName() == d.getPackageName();
        }
        if(!this.isPrivate()){
            return c.getPackageName() == d.getPackageName();
        }
        return c == d;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(char accessFlags) {
        this.accessFlags = accessFlags;
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

    public SoClass getSoClass() {
        return soClass;
    }

    public void setSoClass(SoClass soClass) {
        this.soClass = soClass;
    }

    public boolean isPublic(){
        return 0 != (this.accessFlags&AccessFlagConstant.ACC_PUBLIC);
    }

    public boolean isPrivate(){
        return 0 != (this.accessFlags&AccessFlagConstant.ACC_PRIVATE);
    }

    public boolean isProtected(){
        return 0 != (this.accessFlags&AccessFlagConstant.ACC_PROTECTED);
    }

    public boolean isStatic(){
        return 0 != (this.accessFlags&AccessFlagConstant.ACC_STATIC);
    }

    public boolean isFinal(){
        return 0!=(this.accessFlags&AccessFlagConstant.ACC_FINAL);
    }

    public boolean isSynthetic(){
        return 0 != (this.accessFlags&AccessFlagConstant.ACC_SYNTHETIC);
    }

}