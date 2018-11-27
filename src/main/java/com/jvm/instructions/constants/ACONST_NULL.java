package com.jvm.instructions.constants;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 把null引用推入操作数栈顶
 */
public class ACONST_NULL extends NoOperandsInstruction{
    public void execute(Frame frame){
        frame.getOperandStack().pushRef(null);
    }
}
