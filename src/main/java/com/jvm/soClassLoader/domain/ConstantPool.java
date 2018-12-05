package com.jvm.soClassLoader.domain;

import com.jvm.classReader.model.constant.*;

/**
 * @author luao
 * @date 2018-11-12 16:07
 */
public class ConstantPool {
    public SoClass soClass;

    public Object[] constants;

    public SoClass getSoClass() {
        return soClass;
    }

    public void setSoClass(SoClass soClass) {
        this.soClass = soClass;
    }

    public Object[] getConstants() {
        return constants;
    }

    public void setConstants(Object[] constants) {
        this.constants = constants;
    }

    public ConstantPool(){}

    public ConstantPool(SoClass soClass,Object[] constants){
        this.soClass = soClass;
        this.constants = constants;
    }
    /**
     * 把class文件中的常量池转换成运行时常量池
     * @param soClass
     * @param cfCps
     * @return
     */
    public ConstantPool newConstantPool(SoClass soClass, com.jvm.classReader.ConstantPool cfCps){
        int cpCount = cfCps.getConstantPoolCount();
        if (cpCount<=0){
            return null;
        }
        Object[] constants = new Object[cpCount];
        ConstantPool constantPool = new ConstantPool(soClass,constants);
        for (int i = 0; i <cpCount ; i++) {
            ConstantInfo cpInfo = cfCps.getInfos()[i];
            if(cpInfo instanceof ConstantIntegerInfo){
                constants[i] = ((ConstantIntegerInfo)cpInfo).getVal();
            }else if(cpInfo instanceof ConstantFloatInfo){
                constants[i] = ((ConstantFloatInfo)cpInfo).getVal();
            }else if(cpInfo instanceof ConstantLongInfo){
                constants[i] = ((ConstantLongInfo)cpInfo).getVal();
            }else if(cpInfo instanceof ConstantDoubleInfo){
                constants[i] = ((ConstantDoubleInfo)cpInfo).getVal();
            }else if(cpInfo instanceof ConstantStringInfo){
                constants[i] = ((ConstantStringInfo) cpInfo).getString();
            }else if(cpInfo instanceof ConstantClassInfo){
                constants[i] = ((ClassRef)constants[i]).newClassRef(constantPool,(ConstantClassInfo)cpInfo);
            }else if(cpInfo instanceof ConstantFieldRefInfo){
                constants[i] = ((FieldRef)constants[i]).newFieldRef(constantPool,(ConstantFieldRefInfo) cpInfo);
            }else if(cpInfo instanceof ConstantMethodRefInfo){
                constants[i] = ((MethodRef)constants[i]).newMethodRef(constantPool,(ConstantMethodRefInfo)cpInfo);
            }else if(cpInfo instanceof ConstantInterfaceMethodRefInfo){
                constants[i] = ((InterfaceMethodRef)constants[i]).newInterfaceMethodRef(constantPool,(ConstantInterfaceMethodRefInfo)cpInfo);
            }else {
            }
        }
        return constantPool;
    }

    /**
     * 根据索引返回常量
     * @param index
     * @return
     */
    public Object getConstant(int index){
        if(constants.length<index+1||constants[index]==null){
            //TODO 调用终止指令
            System.err.println("No constants at index "+index);
            System.exit(-1);
        }
        return constants[index];
    }

}
