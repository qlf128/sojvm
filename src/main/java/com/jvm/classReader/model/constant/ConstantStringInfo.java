package com.jvm.classReader.model.constant;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantPool;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class ConstantStringInfo extends ConstantInfo {
    ConstantPool constantPool;
    int stringIndex;

    public ConstantStringInfo(ConstantPool constantPool, int i) {
        this.constantPool = constantPool;
        type = i;
    }


    //读取常量池索引
    @Override
    void readInfo(ClassReader reader) {
        stringIndex = reader.readUint16();
    }

    public String getString() {
        return constantPool.getUtf8(stringIndex);
    }
}
