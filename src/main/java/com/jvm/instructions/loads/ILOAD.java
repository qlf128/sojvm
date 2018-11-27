package com.jvm.instructions.loads;

import com.jvm.instructions.Index8Instruction;
import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ILOAD extends Index8Instruction{

    public void execute(Frame frame){
        IloadUtil.iload(frame, this.getIndex());
    }
}
