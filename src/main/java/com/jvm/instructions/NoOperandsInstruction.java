package com.jvm.instructions;

import com.jvm.runTimeDateArea.model.Frame;

/**
 * 不含操作数的Instructions
 */
public abstract class NoOperandsInstruction implements Instructions{
    public void fetchOperands(BytecodeReader byteCodeReader){
        //nothing to do
    }

    public abstract void execute(Frame frame);

}
