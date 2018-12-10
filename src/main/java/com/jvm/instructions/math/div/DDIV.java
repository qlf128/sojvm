package com.jvm.instructions.math.div;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class DDIV extends NoOperandsInstruction{

    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        double v1 = stack.popDouble();
        double v2 = stack.popDouble();
        double result = v1/v2;
        stack.pushDouble(result);
    }
}
