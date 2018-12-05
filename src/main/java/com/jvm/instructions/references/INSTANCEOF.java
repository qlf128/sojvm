package com.jvm.instructions.references;

import com.jvm.instructions.Index16Instruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.soClassLoader.domain.ClassRef;
import com.jvm.soClassLoader.domain.ConstantPool;
import com.jvm.soClassLoader.domain.SoClass;

public class INSTANCEOF extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        SoObject ref = stack.popObj();
        if (ref == null){
            stack.pushInt(0);
            return;
        }
        ConstantPool cp = frame.getMethod().getSoClass().getConstantPool();
        ClassRef classRef = (ClassRef) cp.getConstant(super.getIndex());
        SoClass soClass = classRef.resolvedClass();
        if (ref.isInstanceOf(soClass)){
            stack.pushInt(1);
        }else {
            stack.pushInt(0);
        }
    }
}
