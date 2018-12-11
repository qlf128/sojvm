package com.jvm.instructions.stores.astore;

import com.jvm.instructions.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoObject;

public class ASTORE extends Index8Instruction {

    public void execute(Frame frame){
        SoObject value = frame.getOperandStack().popObj();
        frame.getLocalVars().setObj(this.getIndex(), value);
    }


}
