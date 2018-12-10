package com.jvm.instructions.math.sh;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class LUSHR extends NoOperandsInstruction{

    /**
     * 逻辑右移（int）
     * @param frame
     */
    public void execute(Frame frame){
        OperandStack operandStack = frame.getOperandStack();

        int v2 = operandStack.popInt();
        long v1 = operandStack.popLong();

        long s = v2 & 0x3f;
        long result = v1 >>> s;
        operandStack.pushLong(result);
    }
}
