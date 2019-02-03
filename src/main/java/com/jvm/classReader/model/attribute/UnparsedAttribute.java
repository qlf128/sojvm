package com.jvm.classReader.model.attribute;

import com.jvm.classReader.ClassReader;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class UnparsedAttribute extends AttributeInfo {
    private String attrName;
    private int attrLen;
    private byte[] info;

    public UnparsedAttribute(String attrName, int attrLen) {
        this.attrName = attrName;
        this.attrLen = attrLen;
    }

    @Override
    void readInfo(ClassReader reader) {
        info = reader.readBytes(attrLen);
    }
}
