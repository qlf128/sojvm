package com.jvm.instructions.constants;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 把int型4推入操作数栈顶
 */
public class ICONST_4 extends NoOperandsInstruction {
    public void execute(Frame frame){
        frame.getOperandStack().pushInt(4);
    }
}
