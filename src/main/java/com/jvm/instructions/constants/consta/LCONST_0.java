package com.jvm.instructions.constants.consta;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 把long型0推入操作数栈顶
 */
public class LCONST_0 extends NoOperandsInstruction {
    public void execute(Frame frame){
        frame.getOperandStack().pushLong(0l);
    }
}
