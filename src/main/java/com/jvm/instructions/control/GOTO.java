package com.jvm.instructions.control;

import com.jvm.instructions.base.instruction.BranchInstruction;
import com.jvm.instructions.base.BranchLogic;
import com.jvm.runTimeDateArea.model.Frame;

public class GOTO extends BranchInstruction{
    public void execute(Frame frame){
        BranchLogic.branch(frame, this.getOffset());
    }
}
