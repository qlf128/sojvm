package com.jvm.instructions.constants;

import com.jvm.instructions.BytecodeReader;
import com.jvm.instructions.Instructions;
import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 从操作数中获取一个byte型整数，扩展成int型，然后推入栈顶
 */
public class BIPUSH implements Instructions {
    private int val;

    public void fetchOperands(BytecodeReader byteCodeReader){
        this.val = byteCodeReader.readInt8();
    }

    public void execute(Frame frame){
        int i = val;
        frame.getOperandStack().pushInt(i);
    }
}
