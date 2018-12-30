package com.jvm.instructions.constants.ipush;

import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.instruction.Instructions;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 从操作数中获 取一个short型整数，扩展成int型，然后推入栈顶
 */
public class SIPUSH implements Instructions {
    private int val;

    public void fetchOperands(BytecodeReader byteCodeReader){
        this.val = byteCodeReader.readInt16();
    }

    public void execute(Frame frame){
        int i = val;
        frame.getOperandStack().pushInt(i);
    }
}
