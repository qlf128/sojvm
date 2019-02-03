package com.jvm.instructions.loads.fload;

import com.jvm.instructions.base.instruction.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class FLOAD_1 extends Index8Instruction{

    public void execute(Frame frame){
        FloadUtil.lload(frame,1);
    }
}
