package com.jvm.instructions.conversions.i2x;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class I2D extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        int i = stack.popInt();
        double d = i;
        stack.pushDouble(d);
    }
}
