package com.jvm.classReader.model.attribute;

import com.jvm.classReader.ClassReader;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class ExceptionsAttribute extends AttributeInfo {

    int[] exceptionIndexTable;

    @Override
    void readInfo(ClassReader reader) {
        exceptionIndexTable = reader.readUint16s();
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }
}
