package com.jvm.instructions.math.sh;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class IUSHR extends NoOperandsInstruction{

    /**
     * 逻辑右移（int）
     * @param frame
     */
    public void execute(Frame frame){
        OperandStack operandStack = frame.getOperandStack();

        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();

        int s = v2 & 0x1f;
        int result = v1 >>> s;
        operandStack.pushInt(result);
    }
}
