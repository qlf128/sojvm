package com.jvm.classReader.model;

import com.jvm.classReader.ConstantPool;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/17 15:03
 */
public abstract class BasicInfo {

    protected ConstantPool mCp;

    public BasicInfo(ConstantPool cp) {
        mCp = cp;
    }

    public abstract void read(InputStream inputStream);
}
