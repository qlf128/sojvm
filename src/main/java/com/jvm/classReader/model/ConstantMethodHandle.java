package com.jvm.classReader.model;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantInfo;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/18 14:53
 */
public class ConstantMethodHandle extends ConstantInfo {

    public short referenceKind;
    public int referenceIndex;

    @Override
    public void read(InputStream inputStream) {
        referenceKind = ClassReader.readU1(inputStream);
        referenceIndex = ClassReader.readU2(inputStream);
    }
}