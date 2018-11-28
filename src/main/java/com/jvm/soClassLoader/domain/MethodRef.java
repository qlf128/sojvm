package com.jvm.soClassLoader.domain;

import com.jvm.classReader.model.constant.ConstantMethodRefInfo;
import com.sun.source.tree.MemberReferenceTree;

/**
 * @author luao
 * @date 2018-11-12 18:30
 */
public class MethodRef extends MemberRef {


    public MethodRef newMethodRef(ConstantPool constantPool, ConstantMethodRefInfo refInfo){
        MethodRef ref = new MethodRef();
        ref.setCp(this.getCp());
        ref.copyMemberRefInfo(refInfo);
        return ref;
    }

}
