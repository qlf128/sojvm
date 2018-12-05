package com.jvm.instructions.references;

import com.jvm.instructions.ClassInitLogic;
import com.jvm.instructions.Index16Instruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.LocalVars;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.soClassLoader.domain.*;

public class GET_STATIC extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getSoClass().getConstantPool();
        FieldRef fieldRef = (FieldRef) cp.getConstant(super.getIndex());
        Field field = fieldRef.resolvedField();
        SoClass soClass = field.getSoClass();
        if (! soClass.isInitStarted()){
            frame.revertNextPC();
            ClassInitLogic.initClass(frame.getThread(), soClass);
            return;
        }
        if (! field.isStatic()){
            throw new RuntimeException("IncompatibleClassChangeError");
        }
        String descriptor = field.getDescriptor();
        int slotId = field.getSoltId();
        LocalVars localVars = soClass.getStaticVars();
        OperandStack stack = frame.getOperandStack();

        switch (descriptor.charAt(0)){
            case 'Z': case 'B': case 'C': case 'S': case 'I':
                stack.pushInt(localVars.getInt(slotId));
                break;
            case 'F':
                stack.pushFloat(localVars.getFloat(slotId));
                break;
            case 'J':
                stack.pushLong(localVars.getLong(slotId));
                break;
            case 'D':
                stack.pushDouble(localVars.getDouble(slotId));
                break;
            case 'L': case '[':
                stack.pushObj(localVars.getObj(slotId));
                break;
                default:
                    //do nothing
                    break;
        }

    }
}
