package com.jvm.instructions.stores.istore;

import com.jvm.instructions.base.instruction.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ISTORE extends Index8Instruction {

    public void execute(Frame frame){
        int value = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(this.getIndex(), value);
    }


}
