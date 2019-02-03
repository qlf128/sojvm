package com.jvm.instructions.loads.dload;

import com.jvm.instructions.base.instruction.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class DLOAD_3 extends Index8Instruction{

    public void execute(Frame frame){
        DloadUtil.lload(frame, 2);
    }
}
