package com.jvm.instructions.math.rem;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.util.NativeMethodUtil;

import static java.lang.Math.abs;

/**
 * Double求余(REM)
 */
public class FREM extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float v2 = stack.popFloat();
        float v1 = stack.popFloat();

        if(v2 == 0){
            NativeMethodUtil.panic("java.lang.ArithmeticException: / by zero");
        }

        float result = v1 % v2;
        stack.pushFloat(result);
    }
}
