package com.jvm.instructions.loads;

import com.jvm.instructions.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class FLOAD extends Index8Instruction{

    public void execute(Frame frame){
        FloadUtil.lload(frame,this.getIndex());
    }
}
