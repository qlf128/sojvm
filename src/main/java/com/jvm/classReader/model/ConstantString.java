package com.jvm.classReader.model;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantInfo;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/18 14:55
 */
public class ConstantString extends ConstantInfo {
    int nameIndex;

    @Override
    public void read(InputStream inputStream) {
        nameIndex = ClassReader.readU2(inputStream);
    }
}