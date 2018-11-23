package com.jvm.classReader.model.attribute;

import com.jvm.classReader.ClassReader;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class ConstantValueAttribute extends AttributeInfo {

    int constantValueIndex;

    @Override
    void readInfo(ClassReader reader) {
        constantValueIndex = reader.readUint16();
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }
}
