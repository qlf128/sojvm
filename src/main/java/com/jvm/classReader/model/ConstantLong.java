package com.jvm.classReader.model;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantInfo;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/18 14:51
 */
public class ConstantLong extends ConstantInfo {
    public long highValue;
    public long lowValue;

    @Override
    public void read(InputStream inputStream) {
        highValue = ClassReader.readU4(inputStream);
        lowValue = ClassReader.readU4(inputStream);
    }
}

