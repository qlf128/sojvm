package com.jvm.classReader.model.attribute;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantPool;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class SourceFileAttribute extends AttributeInfo {

    //sourcefile_index是常量池索引，指向CONSTANT_Utf8_info常量，其常量值是源码文件的文件名
    int sourceFileIndex;
    ConstantPool constantPool;

    public SourceFileAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    void readInfo(ClassReader reader) {
        sourceFileIndex = reader.readUint16();
    }

    public String getFileName() {
        return constantPool.getUtf8(sourceFileIndex);
    }
}
