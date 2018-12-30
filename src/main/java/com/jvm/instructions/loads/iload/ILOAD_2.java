package com.jvm.instructions.loads.iload;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ILOAD_2 extends NoOperandsInstruction {
    
    public void execute(Frame frame){
        IloadUtil.iload(frame, 2);
    }
}
