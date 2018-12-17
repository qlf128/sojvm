package com.jvm.instructions.constants.nop;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 啥也不做的指令
 */
public class NOP extends NoOperandsInstruction{
    public void execute(Frame frame){
        //啥也不做
    }
}
