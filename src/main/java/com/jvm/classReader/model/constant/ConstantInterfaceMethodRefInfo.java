package com.jvm.classReader.model.constant;

import com.jvm.classReader.ConstantPool;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class ConstantInterfaceMethodRefInfo extends ConstantMemberRefInfo {
    public ConstantInterfaceMethodRefInfo(ConstantPool constantPool, int type) {
        super(constantPool, type);
    }
}
