package com.jvm.instructions.loads.iload;

import com.jvm.instructions.base.instruction.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ILOAD extends Index8Instruction{

    public void execute(Frame frame){
        IloadUtil.iload(frame, this.getIndex());
    }
}
