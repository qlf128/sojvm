package com.jvm.instructions.math.rem;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.util.NativeMethodUtil;

public class LREM extends NoOperandsInstruction {

    public void execute(Frame frame){
        OperandStack operandStack = frame.getOperandStack();
        long v2 = operandStack.popLong();
        long v1 = operandStack.popLong();

        if(v2 == 0){
            NativeMethodUtil.panic("java.lang.ArithmeticException: / by zero");
        }

        long result = v1 % v2;
        operandStack.pushLong(result);
    }
}
