package com.jvm.instructions.conversions.d2x;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class D2F extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        double d = stack.popDouble();
        float i = (float)d;
        stack.pushFloat(i);
    }
}
