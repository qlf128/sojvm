package com.jvm.instructions.constants.consta;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 把double型1推入栈顶
 */
public class DCONST_1 extends NoOperandsInstruction {
    public void execute(Frame frame){
        frame.getOperandStack().pushDouble(1d);
    }
}