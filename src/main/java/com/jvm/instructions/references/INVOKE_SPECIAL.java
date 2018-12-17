package com.jvm.instructions.references;

import com.jvm.instructions.Index16Instruction;
import com.jvm.instructions.MethodInvokeLogic;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.soClassLoader.domain.*;
import com.jvm.soClassLoader.util.MethodLookupUtil;

public class INVOKE_SPECIAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        SoClass currentClass = frame.getMethod().getSoClass();
        ConstantPool cp = currentClass.getConstantPool();
        MethodRef methodRef = (MethodRef) cp.getConstant(super.getIndex());
        SoClass resolvedClass = methodRef.resolvedClass();
        Method resolvedMethod = methodRef.resolvedMethod();
        if (resolvedMethod.getName().equals("<init>") &&
                resolvedMethod.getSoClass() != resolvedClass){
            //解析出的方法名字是<init>,表名这是一个构造方法，但方法声明的类却不是解析出的类，此时应抛出异常
            throw new RuntimeException("NoSuchMethodError");
        }
        if (resolvedMethod.isStatic()){
            //invokespecial不处理static类型方法
            throw new RuntimeException("IncompatibleClassChangeError");
        }
        SoObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount()-1);
        if (ref == null){
            throw new RuntimeException("NullPointerException");
        }
        //protected访问控制符：限制同一个类 同一个包 或是 子类 中访问方法
        //TODO 待校验 第二个判断条件：方法所在类是当前类父类要抛异常？
        if (resolvedMethod.isProtected() &&
            ClassHierarchy.isSuperClassOf(resolvedMethod.getSoClass(),currentClass) &&
            !resolvedMethod.getSoClass().getPackageName().equals(currentClass.getPackageName()) &&
            ref.getSoClass() != currentClass &&
            ! ref.getSoClass().isSubClassOf(currentClass)){
            //此处判断逻辑是在履行java关于protected方法只能被声明该方法的类或子类调用的约定
            throw new RuntimeException("IllegalAccessError");
        }
        Method methodToBeInvoked = resolvedMethod;
        //如果当前类的ACC_SUPER标志被设置，调用的是超类的函数，但不是构造函数，需要一个额外的过程查找最终要调用的方法
        if (currentClass.isSuper() &&
            ClassHierarchy.isSuperClassOf(resolvedClass, currentClass) &&
            ! resolvedMethod.getName().equals("<init>")){
            methodToBeInvoked = MethodLookupUtil.lookupMethodInClass(
                    currentClass.getSuperClass(), methodRef.getName(), methodRef.getDescriptor());
        }
        //如果查找失败，或找到的方法是abstract的，抛出异常
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()){
            throw new RuntimeException("AbstractMethodError");
        }
        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }
}
