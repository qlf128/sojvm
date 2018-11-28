package com.jvm.soClassLoader.domain;

import com.jvm.classReader.model.constant.ConstantFieldRefInfo;

/**
 * @author luao
 * @date 2018-11-12 18:29
 */
public class FieldRef extends MemberRef {
    private Field field;

    public FieldRef newFieldRef(ConstantPool constantPool, ConstantFieldRefInfo refInfo){
        FieldRef ref = new FieldRef();
        ref.setCp(this.getCp());
        ref.copyMemberRefInfo(refInfo);
        return ref;
    }

    /**
     * 解析字段符号
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public Field resolvedField() throws ClassNotFoundException,IllegalAccessException,NoSuchFieldException{
        if(this.field == null){
            this.resolveFieldRef();
        }
        return this.field;
    }

    /**
     * 解析字段符号引用
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private void resolveFieldRef() {
        SoClass d = this.getCp().soClass;
        SoClass c = this.resolvedClass();
        Field field = this.lookupField(c,this.getName(),this.getDescriptor());
        if(field == null){
            throw new NoSuchFieldError();
        }
        if(field.isAccessibleTo(d)){
            throw new IllegalAccessError();
        }
        this.field = field;
    }

    /**
     * 字段查找
     * 如果类D想通过字段符号引用访问类C的某个字段
     * 1.首先要解析符号引用得到类C，然后根据字段名和描述符查找字段。
     * 如果字段查找失败，则虚拟机抛出NoSuchFieldError异常。
     * 如果查找成功，但D没有足够的权限访问该字段，则虚拟机抛出IllegalAccessError异常。
     * @param soClass
     * @param name
     * @param descriptor
     * @return
     */
    public Field lookupField(SoClass soClass,String name,String descriptor){
        Field[] fields = soClass.getFields();
        for (int i = 0; i < fields.length; i++) {
            if(name.equals(fields[i].getName()) && descriptor.equals(field.getDescriptor())){
                return field;
            }
        }
        for (SoClass inter : soClass.getInterfaces()) {
            Field field = lookupField(inter,name,descriptor);
            if(field!=null){
                return field;
            }
        }
        if(soClass.getSuperClass() != null){
            return lookupField(soClass.getSuperClass(),name,descriptor);
        }
        return null;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
