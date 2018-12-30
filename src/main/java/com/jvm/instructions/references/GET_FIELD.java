package com.jvm.instructions.references;

import com.jvm.instructions.base.instruction.Index16Instruction;
import com.jvm.runTimeDateArea.model.*;
import com.jvm.soClassLoader.domain.ConstantPool;
import com.jvm.soClassLoader.domain.Field;
import com.jvm.soClassLoader.domain.FieldRef;

public class GET_FIELD extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getSoClass().getConstantPool();
        FieldRef fieldRef = (FieldRef) cp.getConstant(super.getIndex());
        Field field = fieldRef.resolvedField();
        if (field.isStatic()){
            throw new RuntimeException("IncompatibleClassChangeError");
        }
        OperandStack stack = frame.getOperandStack();
        SoObject ref = stack.popObj();
        if (ref == null){
            throw new RuntimeException("NullPointerException");
        }
        String descriptor = field.getDescriptor();
        int slotId = field.getSoltId();
       // Slot[] slots = ref.getLocalVars().getSlots();
        // 新增数组之后的更改
        Slot[] slots  = ref.fields().getSlots();
        //LocalVars localVars = ref.getLocalVars();
        // 新增数组之后的更改
        LocalVars localVars  =ref.fields();

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
                stack.pushRef(localVars.getObj(slotId));
                break;
            default:
                break;
        }
    }
}
