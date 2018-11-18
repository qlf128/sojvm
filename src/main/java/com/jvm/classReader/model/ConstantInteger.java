package com.jvm.classReader.model;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantInfo;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/18 14:50
 */
public class ConstantInteger extends ConstantInfo {
    public long value;

    @Override
    public void read(InputStream inputStream) {
        value = ClassReader.readU4(inputStream);
    }
}