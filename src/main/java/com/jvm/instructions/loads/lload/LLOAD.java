package com.jvm.instructions.loads.lload;

import com.jvm.instructions.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class LLOAD extends Index8Instruction{

    public void execute(Frame frame){
        LloadUtil.lload(frame, this.getIndex());
    }
}
