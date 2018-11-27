package com.jvm.instructions.extended;

import com.jvm.instructions.BytecodeReader;
import com.jvm.instructions.Instructions;
import com.jvm.instructions.base.Base;
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
        Base.branch(frame, this.offset);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
