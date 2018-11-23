package com.jvm.classReader.model.constant;

import com.jvm.classReader.ClassReader;
import com.jvm.util.ByteUtils;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class ConstantLongInfo extends ConstantInfo {
    long val;

    public ConstantLongInfo(int i) {
        type = i;
    }


    @Override
    void readInfo(ClassReader reader) {
        byte[] data = reader.readUint64();
        val = ByteUtils.byteToLong64(data);
    }

    public long getVal() {
        return val;
    }


}
