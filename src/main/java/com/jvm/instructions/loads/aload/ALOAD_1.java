package com.jvm.instructions.loads.aload;

import com.jvm.instructions.base.instruction.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ALOAD_1 extends Index8Instruction{

    public void execute(Frame frame){
        AloadUtil.aload(frame, 1);
    }
}
