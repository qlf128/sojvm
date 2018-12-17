package com.jvm.instructions.loads.dload;

import com.jvm.instructions.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;

public class DLOAD_2 extends Index8Instruction{

    public void execute(Frame frame){
        DloadUtil.lload(frame, 2);
    }
}
