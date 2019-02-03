package com.jvm.classReader.model.constant;

import com.jvm.classReader.ClassReader;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class ConstantMethodTypeInfo extends ConstantInfo {
    //关于byte上界,自行处理;
    private int descriptorIndex;

    public ConstantMethodTypeInfo(int i) {
        type = i;
    }


    @Override
    void readInfo(ClassReader reader) {
        descriptorIndex = reader.readUint16();
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
