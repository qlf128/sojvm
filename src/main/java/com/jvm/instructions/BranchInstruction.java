package com.jvm.instructions;

import com.jvm.runTimeDateArea.model.Frame;

/**
 * 跳转指令
 */
public abstract class BranchInstruction implements Instructions{
    int offset;//跳转偏移量

    public void fetchOperands(BytecodeReader byteCodeReader){
        this.offset = (int)byteCodeReader.readInt16();
    }

    public abstract void execute(Frame frame);

}
