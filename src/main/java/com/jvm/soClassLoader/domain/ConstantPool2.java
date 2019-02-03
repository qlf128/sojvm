package com.jvm.soClassLoader.domain;

/**
 * @author luao
 * @date 2018-11-12 16:07
 */
public class ConstantPool2 {
//    public SoClass soClass;
//
//    public Constant[] constants;
//
//    public ConstantPool2(){}
//
//    public ConstantPool2(SoClass soClass, Constant[] constants){
//        this.soClass = soClass;
//        this.constants = constants;
//    }
//    /**
//     * 把class文件中的常量池转换成运行时常量池
//     * @param soClass
//     * @param cfCps
//     * @return
//     */
//    public ConstantPool2 newConstantPool(SoClass soClass, ClassFile.ConstantPool cfCps){
//        int cpCount = cfCps.constantInfos.length;
//        if (cpCount<=0){
//            return null;
//        }
//        Constant[] constants = new Constant[cpCount];
//        ConstantPool2 constantPool = new ConstantPool2(soClass,constants);
//        for (int i = 0; i <cpCount ; i++) {
//            ClassFile.ConstantInfo cpInfo = cfCps.constantInfos[i];
//            if(cpInfo instanceof ClassFile.ConstantIntegerInfo){
//                constants[i] = ((ClassFile.ConstantIntegerInfo)cpInfo).value();
//            }else if(cpInfo instanceof ClassFile.ConstantFloatInfo){
//                constants[i] = ((ClassFile.ConstantFloatInfo)cpInfo).value();
//            }else if(cpInfo instanceof ClassFile.ConstantLongInfo){
//                constants[i] = ((ClassFile.ConstantLongInfo)cpInfo).value();
//            }else if(cpInfo instanceof ClassFile.ConstantDoubleInfo){
//                constants[i] = ((ClassFile.ConstantDoubleInfo)cpInfo).value();
//            }else if(cpInfo instanceof ClassFile.ConstantStringInfo){
//                constants[i] = ((ClassFile.ConstantStringInfo) cpInfo).string();
//            }else if(cpInfo instanceof ClassFile.ConstantClassInfo){
//                constants[i] = ((ClassRef)constants[i]).newClassRef(constantPool,(ClassFile.ConstantClassInfo)cpInfo);
//            }else if(cpInfo instanceof ClassFile.ConstantFieldrefInfo){
//                constants[i] = ((FieldRef)constants[i]).newFieldRef(constantPool,(ClassFile.ConstantFieldrefInfo)cpInfo);
//            }else if(cpInfo instanceof ClassFile.ConstantMethodrefInfo){
//                constants[i] = ((MethodRef)constants[i]).newMethodRef(constantPool,(ClassFile.ConstantMethodrefInfo)cpInfo);
//            }else if(cpInfo instanceof ClassFile.ConstantInterfaceMethodrefInfo){
//                constants[i] = ((InterfaceMethodRef)constants[i]).newInterfaceMethodRef(constantPool,(ClassFile.ConstantInterfaceMethodrefInfo)cpInfo);
//            }else {
//            }
//        }
//        return constantPool;
//    }
//
//    /**
//     * 根据索引返回常量
//     * @param index
//     * @return
//     */
//    public Constant getConstant(int index){
//        if(constants.length<index+1||constants[index]==null){
//            //TODO 调用终止指令
//            //panic(fmt.Sprintf("No constants at index %d", index))
//        }
//        return constants[index];
//    }
//
//    public static void main(String[] args) {
//        Object[] objs = new Object[10];
//        objs[1] = 1;
//        objs[2] = "2";
//        System.out.println((String)objs[2]);
//
//    }

}
