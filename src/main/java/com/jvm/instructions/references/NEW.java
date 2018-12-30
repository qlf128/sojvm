package com.jvm.instructions.references;

import com.jvm.instructions.base.ClassInitLogic;
import com.jvm.instructions.base.instruction.Index16Instruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.soClassLoader.domain.ClassRef;
import com.jvm.soClassLoader.domain.ConstantPool;
import com.jvm.soClassLoader.domain.SoClass;

public class NEW extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getSoClass().getConstantPool();
        ClassRef classRef = (ClassRef) cp.getConstant(super.getIndex());
        SoClass soClass = classRef.resolvedClass();
        if (! soClass.isInitStarted()){
            frame.revertNextPC();
            ClassInitLogic.initClass(frame.getThread(), soClass);
            return;
        }
        if (soClass.isInterface() || soClass.isAbstract()){
            throw new RuntimeException("InstantiationError");
        }
        SoObject ref = soClass.createObject();
        frame.getOperandStack().pushRef(ref);
    }
}
