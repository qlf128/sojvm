package com.jvm.instructions.loads.iload;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ILOAD_3 extends NoOperandsInstruction {
    
    public void execute(Frame frame){
        IloadUtil.iload(frame, 3);
    }
}
