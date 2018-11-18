package com.jvm.classReader.model;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantPool;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/18 15:02
 */
public class MemberInfo extends BasicInfo {
    public int accessFlags;
    public int nameIndex;
    public int descriptorIndex;
    public int attributesCount;
    public AttributeInfo[] attributes;

    public MemberInfo(ConstantPool cp) {
        super(cp);
    }

    @Override
    public void read(InputStream inputStream) {
        accessFlags = ClassReader.readU2(inputStream);
        nameIndex = ClassReader.readU2(inputStream);
        descriptorIndex = ClassReader.readU2(inputStream);
        attributesCount = ClassReader.readU2(inputStream);
        attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            AttributeInfo attributeInfo = AttributeInfo.getAttribute(mCp, inputStream);
            attributeInfo.read(inputStream);
            attributes[i] = attributeInfo;
        }
    }
}
