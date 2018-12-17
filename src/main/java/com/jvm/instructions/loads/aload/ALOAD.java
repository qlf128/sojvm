package com.jvm.instructions.loads.aload;

import com.jvm.instructions.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ALOAD extends Index8Instruction{

    public void execute(Frame frame){
        AloadUtil.aload(frame, this.getIndex());
    }
}
