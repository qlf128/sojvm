package com.jvm.instructions.constants.consta;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 把int型3推入操作数栈顶
 */
public class ICONST_3 extends NoOperandsInstruction {
    public void execute(Frame frame){
        frame.getOperandStack().pushInt(3);
    }
}
