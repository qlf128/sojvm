package com.jvm.instructions.conversions.l2x;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class L2F extends NoOperandsInstruction {
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        long l = stack.popLong();
        float f = l;
        stack.pushFloat(f);
    }
}
