package com.jvm.classReader.model.constant;

import com.jvm.classReader.ClassReader;
import com.jvm.util.ByteUtils;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class ConstantIntegerInfo extends ConstantInfo {
    int val;

    public ConstantIntegerInfo(int i) {
        type = i;
    }

    @Override
    void readInfo(ClassReader reader) {
        byte[] data = reader.readUint32();
        val = ByteUtils.byteToInt32(data);
    }


    public int getVal() {
        return val;
    }
}
