package com.jvm.instructions.conversions.f2x;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class F2I extends NoOperandsInstruction {
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        float f = stack.popFloat();
        int i = (int)f;
        stack.pushInt(i);
    }
}
