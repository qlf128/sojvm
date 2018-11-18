package com.jvm.classReader.model;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantInfo;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/18 14:51
 */
public class ConstantInvokeDynamic extends ConstantInfo {

    public int bootstrapMethodAttrIndex;
    public int nameAndTypeIndex;

    @Override
    public void read(InputStream inputStream) {
        bootstrapMethodAttrIndex = ClassReader.readU2(inputStream);
        nameAndTypeIndex = ClassReader.readU2(inputStream);
    }
}