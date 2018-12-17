package com.jvm.instructions.math.add;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class FADD extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        float v1 = stack.popFloat();
        float v2 = stack.popFloat();
        float result = v1+v2;
        stack.pushFloat(result);
    }
}
