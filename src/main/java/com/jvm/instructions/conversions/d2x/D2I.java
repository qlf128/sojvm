package com.jvm.instructions.conversions.d2x;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

/**
 * double强制转换成int
 */
public class D2I extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        double d = stack.popDouble();
        int i = (int)d;
        stack.pushInt(i);
    }
}
