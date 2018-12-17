package com.jvm.instructions.conversions.f2x;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class F2L extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        float f = stack.popFloat();
        long l = (long)f;
        stack.pushLong(l);
    }
}
