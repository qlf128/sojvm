package com.jvm.instructions.stores;

import com.jvm.instructions.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ASTORE extends Index8Instruction {

    public void execute(Frame frame){
        Object value = frame.getOperandStack().popObj();
        frame.getLocalVars().setObj(this.getIndex(), value);
    }


}
