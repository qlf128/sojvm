package com.jvm.instructions.loads;

import com.jvm.instructions.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class DLOAD_0 extends Index8Instruction{

    public void execute(Frame frame){
        DloadUtil.lload(frame, 0);
    }
}
