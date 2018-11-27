package com.jvm.instructions.extended;

import com.jvm.instructions.BranchInstruction;
import com.jvm.instructions.base.Base;
import com.jvm.runTimeDateArea.model.Frame;

public class IFNONNULL extends BranchInstruction {
    public void execute(Frame frame){
        Object ref = frame.getOperandStack().popObj();
        if(ref != null){
            Base.branch(frame, this.getOffset());
        }
    }
}
