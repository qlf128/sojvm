package com.jvm.instructions.math.iinc;

import com.jvm.instructions.BytecodeReader;
import com.jvm.instructions.Instructions;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.LocalVars;

/**
 * 给局部变量表中的int变量增加常量值，局部变量表索引和常量值都由指令的操作数提供
 */
public class IINC implements Instructions {
    private int index;
    private int consta;

    /**
     * 从字节码里读取操作数
     * @param byteCodeReader
     */
    public void fetchOperands(BytecodeReader byteCodeReader){
        this.index = byteCodeReader.readUint8();
        this.consta = byteCodeReader.readInt8();
    }

    /**
     * 从局部变量表中读取变量，给它加上常量值，再把结果写回局部变量表
     * @param frame
     */
    public void execute(Frame frame){
        LocalVars localVars = frame.getLocalVars();

        int val = localVars.getInt(index);
        val += consta;
        localVars.setInt(index,val);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getConsta() {
        return consta;
    }

    public void setConsta(int consta) {
        this.consta = consta;
    }
}
