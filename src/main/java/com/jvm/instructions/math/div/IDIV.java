package com.jvm.instructions.math.div;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.util.NativeMethodUtil;

public class IDIV extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        int v1 = stack.popInt();
        int v2 = stack.popInt();
        if(v2 == 0){
            NativeMethodUtil.panic("java.lang.ArithmeticException: / by zero");
        }
        int result = v1 / v2;
        stack.pushInt(result);
    }
}
