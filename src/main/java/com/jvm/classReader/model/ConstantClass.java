package com.jvm.classReader.model;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantInfo;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/17 14:45
 */
public class ConstantClass extends ConstantInfo {

    public int nameIndex;

    @Override
    public void read(InputStream inputStream) {
        nameIndex = ClassReader.readU2(inputStream);
    }
}
