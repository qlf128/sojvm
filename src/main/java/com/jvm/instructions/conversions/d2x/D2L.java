package com.jvm.instructions.conversions.d2x;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.util.DataTypeConvertUtil;

public class D2L extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        double d = stack.popDouble();
        long i = (long)d;
        stack.pushLong(i);
    }
}
