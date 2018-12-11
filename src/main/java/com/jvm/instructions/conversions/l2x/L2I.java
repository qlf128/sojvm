package com.jvm.instructions.conversions.l2x;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class L2I extends NoOperandsInstruction {
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        long l = stack.popLong();
        int i = (int)l;
        stack.pushInt(i);
    }
}
