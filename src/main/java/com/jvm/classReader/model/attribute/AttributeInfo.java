package com.jvm.classReader.model.attribute;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantPool;
import com.jvm.util.ByteUtils;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

/*
attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}
*/
public abstract class AttributeInfo {

    /**
     * 抽象方法,具体实现类去实现
     *
     * @param reader
     */
    abstract void readInfo(ClassReader reader);

    /**
     * 单个读取
     *
     * @param reader
     * @param constantPool
     * @return
     */
    private static AttributeInfo readAttribute(ClassReader reader, ConstantPool constantPool) {
        int attrNameIndex = reader.readUint16();
        String attrName = constantPool.getUtf8(attrNameIndex);
        int attrLen = ByteUtils.byteToInt32(reader.readUint32());
        AttributeInfo attrInfo = create(attrName, attrLen, constantPool);
        attrInfo.readInfo(reader);
        return attrInfo;
    }

    public static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool constantPool) {
        int attributesCount = reader.readUint16();
        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = readAttribute(reader, constantPool);
        }
        return attributes;
    }


    private static AttributeInfo create(String attrName, int attrLen, ConstantPool constantPool) {
        if ("Code".equals(attrName)) {
            return new CodeAttribute(constantPool);
        } else if ("ConstantValue".equals(attrName)) {
            return new ConstantValueAttribute();
        } else if ("Deprecated".equals(attrName)) {
            return new DeprecatedAttribute();
        } else if ("Exceptions".equals(attrName)) {
            return new ExceptionsAttribute();
        } else if ("LineNumberTable".equals(attrName)) {
            return new LineNumberTableAttribute();
        } else if ("LocalVariableTable".equals(attrName)) {
            return new LocalVariableTableAttribute();
        } else if ("SourceFile".equals(attrName)) {
            return new SourceFileAttribute(constantPool);
        } else if ("Synthetic".equals(attrName)) {
            return new SyntheticAttribute();
        } else {
            return new UnparsedAttribute(attrName, attrLen);
        }

    }
}
