package com.jvm.classReader.model.constant;

import com.jvm.classReader.ConstantPool;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class ConstantFieldRefInfo extends ConstantMemberRefInfo {
    public ConstantFieldRefInfo(ConstantPool constantPool, int type) {
        super(constantPool, type);
    }
}
