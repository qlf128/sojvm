package com.jvm.classReader.model.constant;

import com.jvm.classReader.ClassReader;
import com.jvm.classReader.ConstantPool;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

/*
CONSTANT_Fieldref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}
 */
public class ConstantMemberRefInfo extends ConstantInfo {
    ConstantPool constantPool;
    int classIndex;
    int nameAndTypeIndex;

    public ConstantMemberRefInfo(ConstantPool constantPool, int type) {
        this.constantPool = constantPool;
        this.type = type;
    }


    @Override
    void readInfo(ClassReader reader) {
        classIndex = reader.readUint16();
        nameAndTypeIndex = reader.readUint16();
    }

    public String getClassName() {
        return constantPool.getClassName(classIndex);
    }

    public String[] getNameAndDescriptor() {
        return constantPool.getNameAndType(nameAndTypeIndex);
    }

    public String getName() {
        return constantPool.getName(nameAndTypeIndex);
    }

    public String getDescriptor() {
        return constantPool.getType(nameAndTypeIndex);
    }


}
