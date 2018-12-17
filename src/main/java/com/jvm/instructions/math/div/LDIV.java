package com.jvm.instructions.math.div;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.util.NativeMethodUtil;

public class LDIV extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        long v1 = stack.popLong();
        long v2 = stack.popLong();
        if(v2 == 0){
            NativeMethodUtil.panic("java.lang.ArithmeticException: / by zero");
        }
        long result = v1 / v2;
        stack.pushLong(result);
    }
}
