package com.jvm.instructions.stores;

import com.jvm.instructions.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class DSTORE extends Index8Instruction {

    public void execute(Frame frame){
        double value = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(this.getIndex(), value);
    }


}
