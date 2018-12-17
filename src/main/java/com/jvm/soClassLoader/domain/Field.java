package com.jvm.soClassLoader.domain;

import com.jvm.classReader.MemberInfo;

/**
 * @author luao
 * @date 2018-11-12 15:25
 */
public class Field extends ClassMember {
    private int soltId;

    private int constValueIndex;


    public static Field[] newFields(SoClass soClass, MemberInfo[] cfFields) {
        Field[] fields = new Field[cfFields.length];
        if (cfFields == null || cfFields.length <= 0) {
            return null;
        }
        for (int i = 0; i < cfFields.length; i++) {
            Field field = new Field();
            field.setSoClass(soClass);
            field.copyClassMember(cfFields[i]);
            field.copyAttributes(cfFields[i]);
            fields[i]=field;
        }
        return fields;
    }

    public void copyAttributes(MemberInfo cfField){
        if(cfField.getConstantValueAttribute()!=null){
            this.constValueIndex = cfField.getConstantValueAttribute().getConstantValueIndex();
        }
    }

    public boolean isLongOrDouble() {
        return "J".equals(this.getDescriptor())||"D".equals(this.getDescriptor());
    }

    public int getSoltId() {
        return soltId;
    }

    public void setSoltId(int soltId) {
        this.soltId = soltId;
    }

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public void setConstValueIndex(int constValueIndex) {
        this.constValueIndex = constValueIndex;
    }
}
