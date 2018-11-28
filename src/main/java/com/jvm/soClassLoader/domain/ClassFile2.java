package com.jvm.soClassLoader.domain;

import java.lang.reflect.Method;

/**
 * @author luao
 * @date 2018-11-12 15:25
 */
public class ClassFile2 {
    public int magic;

    public char minorVersion;

    public char majorVersion;

    public ConstantPool constantPool;

    public char accessFlags;

    public char thisClass;

    public char superClass;

    public char[] interfaces;

    public MemberInfo[] fields;

    public MemberInfo[] methods;

    public AttributeInfo[] attributes;

    public void Parse(byte[] data){

    }
    public void read(ClassReader reader) {



    }

    public void readAndCheckMagic(ClassReader reader) {



    }

    public void readAndCheckVersion(ClassReader reader) {



    }

    public char MinorVersion() {
        return this.minorVersion;
    }

    public char MajorVersion() {
        return this.majorVersion;
    }

    public ConstantPool ConstantPool() {
        return this.constantPool;
    }

    public char AccessFlags() {
        return this.accessFlags;
    }

    public MemberInfo[] Fields() {
        return this.fields;
    }

    public MemberInfo[] Methods() {
        return this.methods;
    }

    public String ClassName() {
        return String.valueOf(this.thisClass);
    }

    public String SuperClassName() {
        return String.valueOf(this.methods);
    }

    public String[] InterfaceNames() {
        return new String[]{};
    }

    public static ClassFile2 parse(byte[] data){
        return new ClassFile2();
    }

    class AttributeInfo{

    }

    class MemberInfo{
        public char accessFlags;

        public String name;

        public String descriptor;

        public CodeAttribute CodeAttribute(){
            return new CodeAttribute();
        }

        public String className(){
            return "";
        }

        public CodeValueAttribute ConstantValueAttribute(){
          return null;
        }
    }

    class CodeValueAttribute{
        public char constantValueIndex;

        public char ConstantValueIndex(){
            return constantValueIndex;
        }
    }

    class ConstantInfo{

    }

//    class ConstantMemberrefInfo{
//        public char tag;
//
//        public char[] info;
//
//        public Constant value(){
//            return null;
//        }
//
//    }

    class ClassReader{}

    class CodeAttribute{
        public ConstantPool cp;
        public char maxStack;
        public char maxLocals;
        public byte[] code;
        public ExceptionTableEntry exceptionTable;
        public AttributeInfo[] attributes;

        public char MaxStack(){
            return this.maxStack;
        }

        public char MaxLocals(){
            return this.maxLocals;
        }

        public byte[] Code(){
            return this.code;
        }
    }

    class ExceptionTableEntry{

    }

    class ConstantClassInfo extends ConstantInfo{
        public String className;
        public String value(){return "11";}

        public String Name(){
            return className;
        }
    }

    class ConstantMethodrefInfo extends ConstantMemberrefInfo{}

    class ConstantFieldrefInfo extends ConstantMemberrefInfo{}

    class ConstantInterfaceMethodrefInfo extends ConstantMemberrefInfo{}

    class ConstantStringInfo extends ConstantInfo{
        public String val;
        public Object string(){
            return val;
        }
    }

    class ConstantDoubleInfo extends ConstantInfo{
        public double val;
        public Object value(){return val;}
    }

    class ConstantLongInfo extends ConstantInfo{
        public long val;
        public Object value(){return val;}
    }

    class ConstantFloatInfo extends ConstantInfo{
        public float val;
        public Object value(){return val;}
    }

    class ConstantIntegerInfo extends ConstantInfo{
        public int val;
        public Object value(){return val;}
    }

    class ConstantMemberrefInfo extends ConstantInfo{
        public ConstantPool cp;
        public int classIndex;
        public int nameAndTypeIndex;
        public void readInfo(ClassReader reader){}
        public String className(){return null;}

        public String name(){return null;}

        public String descriptor(){return null;}

    }

    class ConstantPool{
        public ConstantInfo[] constantInfos;
    }


}
