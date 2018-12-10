package com.jvm.instructions.math.sub;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class LSUB extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        long v2 = stack.popLong();
        long v1 = stack.popLong();
        long result = v1 - v2;
        stack.pushLong(result);
    }
}
