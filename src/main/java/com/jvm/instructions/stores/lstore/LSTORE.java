package com.jvm.instructions.stores.lstore;

import com.jvm.instructions.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class LSTORE extends Index8Instruction {

    public void execute(Frame frame){
        long value = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(this.getIndex(), value);
    }


}
