package com.jvm.instructions.math.rem;

import com.jvm.instructions.InstructionException;
import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.util.NativeMethodUtil;

import static java.lang.Math.abs;

/**
 * Double求余(REM)
 */
public class DREM  extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double v2 = stack.popDouble();
        double v1 = stack.popDouble();

        if(abs(v2) < 10e-8 ){
            NativeMethodUtil.panic("java.lang.ArithmeticException: / by zero");
        }

        double result = v1 % v2;
        stack.pushDouble(result);
    }
}
