package com.jvm.instructions.base.instruction;

import com.jvm.instructions.base.BytecodeReader;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 存储和加载类指令需要根据索引存取局部变量表，索引由单字节操作数给出。
 * 把这类指令抽象成Index8Instruction，用Index字段表示局部变量表索引。
 */
public abstract class Index8Instruction implements Instructions {
    private int index; //索引

    public void fetchOperands(BytecodeReader byteCodeReader){
        this.index = byteCodeReader.readUint8();
    }

    public abstract void execute(Frame frame);


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
