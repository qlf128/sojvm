package com.jvm.classReader;

import com.jvm.classReader.model.MemberInfo;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/13 00:05
 */
public class ClassFile {

    public long magic;
    public int minorVersion;
    public int majorVersion;
    public ConstantPool constantPool;
    public int accessFlag;
    public String className;
    public String superClass;
    public int interfaceCount;
    public String[] interfaces;
    public int fieldCount;
    public MemberInfo[] fields;
    public int methodCount;
    public MemberInfo[] methods;

    public ClassFile() {
    }

    /**
     * 解析ClassFile
     *
     * @param classData
     * @return
     * @throws Exception
     */
    public ClassFile Parse(InputStream classData) throws Exception {
        if (classData == null) {
            throw new Exception("ClassFile InputSream is null");
        }

        return null;
    }

    /**
     * 读取ClassFile
     *
     * @param classData
     * @return
     * @throws Exception
     */
    public ClassFile read(InputStream classData) throws Exception {
        if (classData == null) {
            throw new Exception("ClassFile InputSream is null");
        }

        return null;
    }

    /**
     * 读取并且检查魔数
     *
     * @param classData
     * @throws Exception
     */
    private void readAndCheckMagic(InputStream classData) throws Exception {
        if (classData == null) {
            throw new Exception("ClassFile InputSream is null");
        }

    }

    /**
     * 读取并且检查版本号
     *
     * @param classData
     * @throws Exception
     */
    private void readAndCheckVersion(InputStream classData) throws Exception {
        if (classData == null) {
            throw new Exception("ClassFile InputSream is null");
        }

    }

    /**
     * 副办本号
     *
     * @return
     */
    public int MinorVersion() {

        return 0;
    }

    /**
     * 主版本号
     *
     * @return
     */
    public int MajorVersion() {

        return 0;
    }

    /**
     * 常量池
     *
     * @return
     */
    public ConstantPool ConstantPool() {

        return null;
    }

    /**
     * 访问标志
     *
     * @return
     */
    public int AccessFlags() {

        return 0;
    }

    /**
     * 变量
     *
     * @return
     */
    public MemberInfo[] Fields() {

        return null;
    }

    /**
     * 方法
     *
     * @return
     */
    public MemberInfo[] Methods() {

        return null;
    }

    /**
     * 类名
     *
     * @return
     */
    public String ClassName() {
        return null;
    }

    /**
     * 父类名
     *
     * @return
     */
    public String SuperClassName() {
        return null;
    }

    /**
     * 接口名
     *
     * @return
     */
    public String[] InterfaceNames() {
        return null;
    }

}