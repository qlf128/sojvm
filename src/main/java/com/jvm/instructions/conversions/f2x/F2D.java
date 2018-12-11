package com.jvm.instructions.conversions.f2x;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class F2D extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        float f = stack.popFloat();
        double d = (double)f;
        stack.pushDouble(d);
    }
}
