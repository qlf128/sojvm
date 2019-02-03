package com.jvm.classReader.model.attribute;

import com.jvm.classReader.ClassReader;


/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class SyntheticAttribute extends AttributeInfo {
    int attribute_name_index;
    int attribute_length;

    @Override
    void readInfo(ClassReader reader) {
    }
}
