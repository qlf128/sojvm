package com.jvm.instructions.math.add;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class DADD extends NoOperandsInstruction{

    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        double v1 = stack.popDouble();
        double v2 = stack.popDouble();
        double result = v1+v2;
        stack.pushDouble(result);
    }
}
