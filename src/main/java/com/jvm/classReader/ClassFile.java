package com.jvm.classReader;

import com.jvm.classReader.model.MemberInfo;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/13 00:05
 */
public class ClassFile {

    public long magic;
    public int minorVersion;
    public int majorVersion;
    //    public ConstantPool constantPool;
    public int accessFlag;
    public String className;
    public String superClass;
    public int interfaceCount;
    public String[] interfaces;
    public int fieldCount;
    public MemberInfo[] fields;
    public int methodCount;
    public MemberInfo[] methods;

    public long Magic() {
        return magic;
    }

    public int MinorVersion() {
        return minorVersion;
    }

    public int MajorVersion() {
        return majorVersion;
    }

    public int AccessFlag() {
        return accessFlag;
    }

    public String ClassName() {
        return className;
    }

    public String SuperClass() {
        return superClass;
    }

    public int InterfaceCount() {
        return interfaceCount;
    }

    public String[] Interfaces() {
        return interfaces;
    }

    public int FieldCount() {
        return fieldCount;
    }

    public MemberInfo[] Fields() {
        return fields;
    }

    public int MethodCount() {
        return methodCount;
    }

    public MemberInfo[] Methods() {
        return methods;
    }
}