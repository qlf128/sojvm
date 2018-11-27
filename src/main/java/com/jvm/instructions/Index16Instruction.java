package com.jvm.instructions;

import com.jvm.runTimeDateArea.model.Frame;

/**
 * 有一些指令需要访问运行时常量池，常量池索引由两个字节操作数给出。
 * 把这类指令抽象成Index16Instruction，用Index字段表示常量池索引。
 */
public abstract class Index16Instruction implements Instructions{
    private int index;

    public void fetchOperands(BytecodeReader byteCodeReader){
        index = byteCodeReader.readUint16();
    }

    public abstract void execute(Frame frame);
}
