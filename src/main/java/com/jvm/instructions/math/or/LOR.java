package com.jvm.instructions.math.or;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class LOR extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        long v2 = stack.popInt();
        long v1 = stack.popInt();
        long result = v1 | v2;
        stack.pushLong(result);
    }
}
