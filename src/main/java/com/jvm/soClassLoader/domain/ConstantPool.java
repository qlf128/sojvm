package com.jvm.soClassLoader.domain;

import com.jvm.classReader.model.constant.*;

/**
 * @author luao
 * @date 2018-11-12 16:07
 */
public class ConstantPool {
    public SoClass soClass;
    public Object[] consts;

    public SoClass getSoClass() {
        return soClass;
    }
    public void setSoClass(SoClass soClass) {
        this.soClass = soClass;
    }
    public Object[] getConstants() {
        return consts;
    }
    public void setConstants(Object[] constants) {
        this.consts = constants;
    }


    public ConstantPool(){}
    public ConstantPool(SoClass soClass,Object[] constants){
        this.soClass = soClass;
        this.consts = constants;
    }

    /**
     * 把class文件中的常量池转换成运行时常量池
     * @param soClass
     * @param cfCp
     * @return
     */
    public static ConstantPool newConstantPool(SoClass soClass, com.jvm.classReader.ConstantPool cfCp){
        int cpCount = cfCp.getConstantPoolCount();
        if (cpCount<=0){
            return null;
        }
        Object[] constants = new Object[cpCount];
        //运行时常量池
        ConstantPool rtcp = new ConstantPool(soClass,constants);
        for (int i = 0; i <cpCount ; i++) {
            ConstantInfo cpInfo = cfCp.getInfos()[i];

            if(cpInfo instanceof ConstantIntegerInfo){
                constants[i] = ((ConstantIntegerInfo)cpInfo).getVal();
            }else if(cpInfo instanceof ConstantFloatInfo){
                constants[i] = ((ConstantFloatInfo)cpInfo).getVal();
            }else if(cpInfo instanceof ConstantLongInfo){
                constants[i] = ((ConstantLongInfo)cpInfo).getVal();
                i++;
            }else if(cpInfo instanceof ConstantDoubleInfo){
                constants[i] = ((ConstantDoubleInfo)cpInfo).getVal();
                i++;
            }else if(cpInfo instanceof ConstantStringInfo){
                constants[i] = ((ConstantStringInfo) cpInfo).getString();
            }else if(cpInfo instanceof ConstantClassInfo){
                ClassRef classRef = new ClassRef();
                ClassRef classRef1 = classRef.newClassRef(rtcp,(ConstantClassInfo) cpInfo);
                constants[i] = classRef1;
            }else if(cpInfo instanceof ConstantFieldRefInfo){
                FieldRef fieldRef = new FieldRef();
                FieldRef fieldRef1 = fieldRef.newFieldRef(rtcp,(ConstantFieldRefInfo) cpInfo);
                constants[i] = fieldRef1;
            }else if(cpInfo instanceof ConstantMethodRefInfo){
                MethodRef methodRef = new MethodRef();
                MethodRef methodRef1 = methodRef.newMethodRef(rtcp, (ConstantMethodRefInfo) cpInfo);
                constants[i] = methodRef1;
            }else if(cpInfo instanceof ConstantInterfaceMethodRefInfo){
                InterfaceMethodRef methodRef = new InterfaceMethodRef();
                InterfaceMethodRef methodRef1 = methodRef.newInterfaceMethodRef(rtcp,(ConstantInterfaceMethodRefInfo)cpInfo);
                constants[i] = methodRef1;
            }else {
            }
        }
        return rtcp;
    }

    /**
     * 根据索引返回常量
     * @param index
     * @return
     */
    public Object getConstant(int index){
        if(consts.length<index+1|| consts[index]==null){
            //TODO 调用终止指令
            System.err.println("No consts at index "+index);
            System.exit(-1);
        }
        return consts[index];
    }

}
