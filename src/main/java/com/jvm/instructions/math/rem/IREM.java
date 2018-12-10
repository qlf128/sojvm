package com.jvm.instructions.math.rem;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.util.NativeMethodUtil;

public class IREM extends NoOperandsInstruction {

    public void execute(Frame frame){
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();

        if(v2 == 0){
            NativeMethodUtil.panic("java.lang.ArithmeticException: / by zero");
        }

        int result = v1 % v2;
        operandStack.pushInt(result);
    }
}
