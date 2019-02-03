package com.jvm.instructions.references;

import com.jvm.instructions.base.instruction.Index16Instruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.soClassLoader.domain.ClassRef;
import com.jvm.soClassLoader.domain.ConstantPool;
import com.jvm.soClassLoader.domain.SoClass;

public class CHECK_CAST extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        SoObject ref = stack.popObj();
        stack.pushRef(ref);
        if (ref == null){
            return;
        }
        ConstantPool cp = frame.getMethod().getSoClass().getConstantPool();
        ClassRef classRef = (ClassRef) cp.getConstant(super.getIndex());
        SoClass soClass = classRef.resolvedClass();
        if (! ref.isInstanceOf(soClass)){
            throw new RuntimeException("sojava.lang.ClassCastException");
        }
    }
}
