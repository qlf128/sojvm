package com.jvm.classReader.model.attribute;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantPool;
import com.jvm.util.ByteUtils;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class CodeAttribute extends AttributeInfo {
    ConstantPool constantPool;
    //操作数栈的最大深度
    int maxStack;
    //局部变量表大小
    int maxLocals;
    //字节码
    byte[] code;
    ExceptionTableEntry[] exceptionTable;
    AttributeInfo[] attributes;

    public CodeAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    void readInfo(ClassReader reader) {
        maxStack = reader.readUint16();
        maxLocals = reader.readUint16();
        int codeLength = ByteUtils.byteToInt32(reader.readUint32());
        code = reader.readBytes(codeLength);
        exceptionTable = readExceptionTable(reader);
        attributes = readAttributes(reader, constantPool);
    }

    private ExceptionTableEntry[] readExceptionTable(ClassReader reader) {
        int exceptionTableLength = reader.readUint16();
        ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptionTable[i] = new ExceptionTableEntry(reader);
        }
        return exceptionTable;
    }

    public LineNumberTableAttribute lineNumberTableAttribute() {
        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i] instanceof LineNumberTableAttribute) {
                return (LineNumberTableAttribute) attributes[i];
            }
        }
        return null;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    //异常表，包含四个指针，分别为
    public static class ExceptionTableEntry {

        int startPc;
        int endPc;
        int handlerPc;
        int catchType;

        public ExceptionTableEntry(ClassReader reader) {
            this.startPc = reader.readUint16();
            this.endPc = reader.readUint16();
            this.handlerPc = reader.readUint16();
            this.catchType = reader.readUint16();
        }

        public int getStartPc() {
            return startPc;
        }

        public int getEndPc() {
            return endPc;
        }

        public int getHandlerPc() {
            return handlerPc;
        }

        public int getCatchType() {
            return catchType;
        }
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public ExceptionTableEntry[] getExceptionTable() {
        return exceptionTable;
    }


}
