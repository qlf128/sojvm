package com.jvm.soClassLoader.domain;

import com.jvm.classReader.model.constant.ConstantClassInfo;

/**
 * @author luao
 * @date 2018-11-12 17:53
 */
public class ClassRef extends SymRef {

    public ClassRef(){}

    public ClassRef newClassRef(ConstantPool cp, ConstantClassInfo constantClassInfo){
        ClassRef ref = new ClassRef();
        ref.setCp(cp);
        ref.setClassName(constantClassInfo.getName());
        return ref;
    }
}
