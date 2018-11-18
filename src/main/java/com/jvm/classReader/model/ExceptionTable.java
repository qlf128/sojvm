package com.jvm.classReader.model;

import com.jvm.classReader.ClassReader;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/18 15:08
 */
public class ExceptionTable {

    public int startPc;
    public int endPc;
    public int handlerPc;
    public int catchType;

    public void read(InputStream inputStream) {
        startPc = ClassReader.readU2(inputStream);
        endPc = ClassReader.readU2(inputStream);
        handlerPc = ClassReader.readU2(inputStream);
        catchType = ClassReader.readU2(inputStream);
    }
}