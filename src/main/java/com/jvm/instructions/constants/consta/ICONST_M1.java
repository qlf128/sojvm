package com.jvm.instructions.constants.consta;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 把int型-1推入操作数栈顶
 */
public class ICONST_M1 extends NoOperandsInstruction {
    public void execute(Frame frame){
        frame.getOperandStack().pushInt(-1);
    }
}
