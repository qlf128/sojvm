package com.jvm.classReader.model;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantInfo;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/18 14:54
 */
public class ConstantMethodType extends ConstantInfo {

    int descType;

    @Override
    public void read(InputStream inputStream) {
        descType = ClassReader.readU2(inputStream);
    }
}