package com.jvm.instructions.constants.ldc;

import com.jvm.instructions.base.instruction.Index16Instruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.soClassLoader.domain.ConstantPool;
import com.jvm.util.NativeMethodUtil;

public class LDC2_W extends Index16Instruction {
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        ConstantPool constantPool = frame.getMethod().getSoClass().getConstantPool();
        Object c = constantPool.getConstant(this.getIndex());

        if(c instanceof Long){
            stack.pushLong((long)c);
        }else if(c instanceof Double){
            stack.pushDouble((double)c);
        }else{
            NativeMethodUtil.panic("LDC2_W error. no such object");
        }

    }
}
