package com.jvm.instructions.constants.consta;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 把float型1推入栈顶
 */
public class FCONST_2 extends NoOperandsInstruction {
    public void execute(Frame frame){
        frame.getOperandStack().pushFloat(2f);
    }
}