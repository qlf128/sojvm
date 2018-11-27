package com.jvm.instructions.loads;

import com.jvm.instructions.BytecodeReader;
import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ILOAD_0 extends NoOperandsInstruction {

    public void execute(Frame frame){
        IloadUtil.iload(frame, 0);
    }
}
