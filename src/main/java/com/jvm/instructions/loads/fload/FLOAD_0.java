package com.jvm.instructions.loads.fload;

import com.jvm.instructions.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class FLOAD_0 extends Index8Instruction{

    public void execute(Frame frame){
        FloadUtil.lload(frame,0);
    }
}
