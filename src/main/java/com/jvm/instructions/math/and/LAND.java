package com.jvm.instructions.math.and;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class LAND extends NoOperandsInstruction{

    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();

        long v2 = stack.popLong();
        long v1 = stack.popLong();

        long result = v1 & v2;
        stack.pushLong(result);
    }
}
