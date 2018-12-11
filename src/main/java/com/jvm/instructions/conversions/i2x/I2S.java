package com.jvm.instructions.conversions.i2x;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

/**
 * convert int to short
 */
public class I2S extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        int i = stack.popInt();
        short s = (short)i;
        stack.pushInt(s);
    }
}
