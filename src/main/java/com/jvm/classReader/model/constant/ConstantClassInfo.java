package com.jvm.classReader.model.constant;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantPool;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class ConstantClassInfo extends ConstantInfo {
    ConstantPool constantPool;
    public int nameIndex;

    public ConstantClassInfo(ConstantPool constantPool, int i) {
        this.constantPool = constantPool;
        type = i;
    }


    @Override
    void readInfo(ClassReader reader) {
        nameIndex = reader.readUint16();
    }

    public String getName() {
        return constantPool.getUtf8(nameIndex);
    }
}
