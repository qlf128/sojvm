package com.jvm.instructions.conversions.i2x;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class I2C extends NoOperandsInstruction {
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        int i = stack.popInt();
        char c = (char)i;
        stack.pushInt(c);
    }
}
