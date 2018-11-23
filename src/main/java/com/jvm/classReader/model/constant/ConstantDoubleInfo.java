package com.jvm.classReader.model.constant;

import com.jvm.classReader.ClassReader;
import com.jvm.util.ByteUtils;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class ConstantDoubleInfo extends ConstantInfo {
    double val;

    public ConstantDoubleInfo(int i) {
        type = i;
    }


    @Override
    void readInfo(ClassReader reader) {
        byte[] data = reader.readUint64();
        val = ByteUtils.byte2Double64(data);
    }

    public double getVal() {
        return val;
    }
}
