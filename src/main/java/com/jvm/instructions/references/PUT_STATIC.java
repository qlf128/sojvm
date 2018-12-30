package com.jvm.instructions.references;

import com.jvm.instructions.base.ClassInitLogic;
import com.jvm.instructions.base.instruction.Index16Instruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.LocalVars;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.soClassLoader.domain.*;

public class PUT_STATIC extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Method currentMethod = frame.getMethod();
        SoClass currentClass = currentMethod.getSoClass();
        ConstantPool cp = currentClass.getConstantPool();
        FieldRef fieldRef = (FieldRef) cp.getConstant(super.getIndex());
        Field  field = fieldRef.resolvedField();
        SoClass soClass = field.getSoClass();
        if (! soClass.isInitStarted()){
            frame.revertNextPC();
            ClassInitLogic.initClass(frame.getThread(), soClass);
            return;
        }
        if (!field.isStatic()){
            throw new RuntimeException("IncompatibleClassChangeError");
        }
        if (field.isFinal()){
            if (currentClass != soClass || !currentMethod.getName().equals("<clinit>")){
                throw new RuntimeException("IllegalAccessError");
            }
        }
        String descriptor = field.getDescriptor();
        int slotId = field.getSoltId();
        LocalVars localVars = soClass.getStaticVars();
        OperandStack stack = frame.getOperandStack();
        switch (descriptor.charAt(0)){
            case 'Z': case 'B': case 'C': case 'S': case 'I':
                localVars.setInt(slotId, stack.popInt());
                break;
            case 'F':
                localVars.setFloat(slotId, stack.popFloat());
                break;
            case 'J':
                localVars.setLong(slotId, stack.popLong());
                break;
            case 'D':
                localVars.setDouble(slotId, stack.popDouble());
                break;
            case 'L': case '[':
                localVars.setObj(slotId, stack.popObj());
                break;
                default:
                    //do nothing
                    break;
        }
    }
}
