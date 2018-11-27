package com.jvm.instructions.control;

import com.jvm.instructions.BranchInstruction;
import com.jvm.instructions.base.Base;
import com.jvm.runTimeDateArea.model.Frame;

public class GOTO extends BranchInstruction{
    public void execute(Frame frame){
        Base.branch(frame, this.getOffset());
    }
}
