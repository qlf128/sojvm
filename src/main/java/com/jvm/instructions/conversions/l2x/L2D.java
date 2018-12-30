package com.jvm.instructions.conversions.l2x;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class L2D extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        long l = stack.popLong();
        double d = l;
        stack.pushDouble(d);
    }
}
