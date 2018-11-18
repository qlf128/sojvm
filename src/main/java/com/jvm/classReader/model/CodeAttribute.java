package com.jvm.classReader.model;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantPool;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/17 15:06
 */
public class CodeAttribute extends AttributeInfo {
    public int maxStack;
    public int maxLocals;
    public int codeLength;
    public short[] code;
    public int excepetionTableLength;
    public ExceptionTable[] exceptionTable;
    public int attributes_count;
    public AttributeInfo[] attributes;


    public CodeAttribute(ConstantPool cp, int nameIndex) {
        super(cp, nameIndex);
    }

    @Override
    public void read(InputStream inputStream) {
        length = (int) ClassReader.readU4(inputStream);

        maxStack = ClassReader.readU2(inputStream);
        maxLocals = ClassReader.readU2(inputStream);
        codeLength = (int) ClassReader.readU4(inputStream);
        code = new short[codeLength];
        for (int i = 0; i < codeLength; i++) {
            code[i] = ClassReader.readU1(inputStream);
        }
        excepetionTableLength = ClassReader.readU2(inputStream);
        exceptionTable = new ExceptionTable[excepetionTableLength];
        for (int i = 0; i < excepetionTableLength; i++) {
            ExceptionTable exceTable = new ExceptionTable();
            exceTable.read(inputStream);
            exceptionTable[i] = exceTable;
        }
        attributes_count = ClassReader.readU2(inputStream);
        attributes = new AttributeInfo[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            AttributeInfo attr = AttributeInfo.getAttribute(mCp, inputStream);
            attr.read(inputStream);
            attributes[i] = attr;
        }

    }
}
