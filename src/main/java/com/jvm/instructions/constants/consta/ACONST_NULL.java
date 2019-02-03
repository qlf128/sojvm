package com.jvm.instructions.constants.consta;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 把null引用推入操作数栈顶
 */
public class ACONST_NULL extends NoOperandsInstruction{
    public void execute(Frame frame){
        frame.getOperandStack().pushRef(null);
    }
}
