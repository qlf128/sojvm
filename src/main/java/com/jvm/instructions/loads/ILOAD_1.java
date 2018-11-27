package com.jvm.instructions.loads;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ILOAD_1 extends NoOperandsInstruction {
    
    public void execute(Frame frame){
        IloadUtil.iload(frame, 1);
    }
}
