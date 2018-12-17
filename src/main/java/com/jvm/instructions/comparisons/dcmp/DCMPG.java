package com.jvm.instructions.comparisons.dcmp;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class DCMPG extends NoOperandsInstruction{
    public void execute(Frame frame){
        DCMP.dcmp(frame,true);
    }

}
