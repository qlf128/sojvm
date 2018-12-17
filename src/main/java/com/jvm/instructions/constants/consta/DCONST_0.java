package com.jvm.instructions.constants.consta;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 把double型0推入栈顶
 */
public class DCONST_0 extends NoOperandsInstruction {
    public void execute(Frame frame){
        frame.getOperandStack().pushDouble(0.0d);
    }
}