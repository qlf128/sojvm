package com.jvm.classReader.model;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantInfo;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/18 14:52
 */
public class ConstantMemberRef extends ConstantInfo {

    public int classIndex;
    public int nameAndTypeIndex;


    @Override
    public void read(InputStream inputStream) {
        classIndex = ClassReader.readU2(inputStream);
        nameAndTypeIndex = ClassReader.readU2(inputStream);
    }
}