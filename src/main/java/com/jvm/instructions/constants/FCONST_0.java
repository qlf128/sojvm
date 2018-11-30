package com.jvm.instructions.constants;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 把float型0推入栈顶
 */
public class FCONST_0 extends NoOperandsInstruction {
    public void execute(Frame frame){
        frame.getOperandStack().pushFloat(0.0f);
    }
}