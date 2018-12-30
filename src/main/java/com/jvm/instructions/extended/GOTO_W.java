package com.jvm.instructions.extended;

import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.instruction.Instructions;
import com.jvm.instructions.base.BranchLogic;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * goto_w指令和goto指令的唯一区别就是索引从2字节变成了4字节
 */
public class GOTO_W implements Instructions{
    private int offset;

    public void fetchOperands(BytecodeReader byteCodeReader){
        this.offset = byteCodeReader.readInt32();
    }

    public void execute(Frame frame){
        BranchLogic.branch(frame, this.offset);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
