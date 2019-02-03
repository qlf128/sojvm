package com.jvm.instructions.references;

import com.jvm.instructions.base.instruction.Index16Instruction;
import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.soClassLoader.domain.*;
import com.jvm.soClassLoader.util.MethodLookupUtil;

public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        SoClass currentClass = frame.getMethod().getSoClass();
        ConstantPool cp = currentClass.getConstantPool();
        MethodRef methodRef = (MethodRef) cp.getConstant(super.getIndex());
        Method resolvedMethod = methodRef.resolvedMethod();
        if (resolvedMethod.isStatic()){
            throw new RuntimeException("IncompatibleClassChangeError");
        }
        SoObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount()-1);
        //hack 方式实现println函数，此时JVMonJava暂不支持
        if (ref == null){
            if (methodRef.getName().equals("println")){
                _println(frame.getOperandStack(), methodRef.getDescriptor());
                return;
            }
            throw new RuntimeException("NullPointerException");
        }
        if (resolvedMethod.isProtected() &&
                ClassHierarchy.isSuperClassOf(resolvedMethod.getSoClass(),currentClass) &&
                !resolvedMethod.getSoClass().getPackageName().equals(currentClass.getPackageName()) &&
                ref.getSoClass() != currentClass && ! ClassHierarchy.isSubClassOf(ref.getSoClass(), currentClass)){
            throw new RuntimeException("IllegalAccessError");
        }
        Method methodToBeInvoked =
                MethodLookupUtil.lookupMethodInClass(ref.getSoClass(), methodRef.getName(), methodRef.getDescriptor());
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()){
            throw new RuntimeException("AbstractMethodError");
        }
        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }

    private void _println(OperandStack stack, String descriptor){
        if (descriptor.equals("(Z)V") || descriptor.equals("(C)V") || descriptor.equals("(I)V") ||
            descriptor.equals("(B)V") || descriptor.equals("(S)V")){
            System.out.println(stack.popInt());
        }else if (descriptor.equals("(F)V")){
            System.out.println(stack.popFloat());
        }else if (descriptor.equals("(J)V")){
            System.out.println(stack.popLong());
        }else if (descriptor.equals("(D)V")){
            System.out.println(stack.popDouble());
        }else {
            throw new RuntimeException("println: "+descriptor);
        }
        stack.popObj();

    }
}
