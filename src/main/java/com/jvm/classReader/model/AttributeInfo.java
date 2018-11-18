package com.jvm.classReader.model;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantPool;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/17 15:06
 */
public class AttributeInfo extends BasicInfo {
    public int nameIndex;
    public int length;
    public short[] info;

    public static final String CODE = "Code";

    public AttributeInfo(ConstantPool cp, int nameIndex) {

        super(cp);
        this.nameIndex = nameIndex;
    }

    @Override
    public void read(InputStream inputStream) {
        length = (int) ClassReader.readU4(inputStream);
        info = new short[length];
        for (int i = 0; i < length; i++) {
            info[i] = ClassReader.readU1(inputStream);
        }
    }

    public static AttributeInfo getAttribute(ConstantPool cp, InputStream inputStream) {
        int nameIndex = ClassReader.readU2(inputStream);
        String name = ((ConstantUtf8) cp.cpInfo[nameIndex]).value;
        switch (name) {
            case CODE:
                return new CodeAttribute(cp, nameIndex);
        }
        return new AttributeInfo(cp, nameIndex);

    }
}

