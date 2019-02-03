package com.jvm.classReader;

import com.jvm.classReader.model.attribute.AttributeInfo;
import com.jvm.classReader.model.attribute.SourceFileAttribute;
import com.jvm.util.ByteUtils;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 19:01
 */

public class ClassFile {

    private int minorVersion;
    private int majorVersion;
    public ConstantPool constantPool;
    private int accessFlags;

    private int thisClass;
    private int superClass;
    private int[] interfaces;
    private MemberInfo[] fields;
    private MemberInfo[] methods;
    private AttributeInfo[] attributes;


    public ClassFile(byte[] classData) {
        ClassReader reader = new ClassReader(classData);
        read(reader);
    }

    /**
     * 读取class文件,解析各个字段
     *
     * @param reader
     */
    private void read(ClassReader reader) {
        readAndCheckMagic(reader);
        readAndCheckVersion(reader);
        constantPool = new ConstantPool(reader);
        accessFlags = reader.readUint16();
        thisClass = reader.readUint16();
        superClass = reader.readUint16();
        interfaces = reader.readUint16s();
        fields = MemberInfo.readMembers(reader, constantPool);
        methods = MemberInfo.readMembers(reader, constantPool);
        attributes = AttributeInfo.readAttributes(reader, constantPool);
    }


    /**
     * 魔数是64位无符号,所以不能简单的使用long来表示,目前是将4个字节的byte数组转换为字符串,对比class文件的魔数
     *
     * @param reader
     */
    private void readAndCheckMagic(ClassReader reader) {
        String magic = ByteUtils.bytesToHexString(reader.readUint32());
        if (!magic.equals("CAFEBABE")) {
            throw new RuntimeException("java.lang.ClassFormatError: magic!");
        }
    }

    /**
     * 版本号
     *
     * @param reader
     */
    private void readAndCheckVersion(ClassReader reader) {
        minorVersion = reader.readUint16();
        majorVersion = reader.readUint16();
        if (majorVersion == 45) {
            return;
        }
        if (minorVersion == 0 && majorVersion >= 46 && majorVersion <= 52) {
            return;
        }
        throw new RuntimeException("java.lang.UnsupportedClassVersionError!");
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getThisClass() {
        return thisClass;
    }

    public int getSuperClass() {
        return superClass;
    }

    public int[] getInterfaces() {
        return interfaces;
    }

    public MemberInfo[] getFields() {
        return fields;
    }

    public MemberInfo[] getMethods() {
        return methods;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public String getClassName() {
        return constantPool.getClassName(thisClass);
    }

    public String getSuperClassName() {
        if (superClass > 0) {
            return constantPool.getClassName(superClass);
        } else {
            return "";
        }
    }

    public String[] getInterfaceNames() {
        String[] interfaceNames = new String[interfaces.length];
        for (int i = 0; i < interfaceNames.length; i++) {
            interfaceNames[i] = constantPool.getClassName(interfaces[i]);
        }
        return interfaceNames;

    }

    public SourceFileAttribute sourceFileAttribute(){
        for(int i=0; i<attributes.length;i++){
            if(attributes[i] instanceof SourceFileAttribute){
                return (SourceFileAttribute)attributes[i];
            }
        }
        return null;
    }
}
