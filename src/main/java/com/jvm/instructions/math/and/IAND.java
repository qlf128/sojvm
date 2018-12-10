package com.jvm.instructions.math.and;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class IAND extends NoOperandsInstruction{

    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();

        int v2 = stack.popInt();
        int v1 = stack.popInt();

        int result = v1 & v2;
        stack.pushInt(result);
    }
}
