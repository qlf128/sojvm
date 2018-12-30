package com.jvm.instructions.comparisons.dcmp;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class DCMPG extends NoOperandsInstruction{
    public void execute(Frame frame){
        DCMP.dcmp(frame,true);
    }

}
