package com.jvm.instructions.loads.dload;

import com.jvm.instructions.base.instruction.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class DLOAD extends Index8Instruction{

    public void execute(Frame frame){
        DloadUtil.lload(frame, this.getIndex());
    }
}
