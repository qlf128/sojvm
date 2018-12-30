package com.jvm.instructions.extended;

import com.jvm.instructions.base.instruction.BranchInstruction;
import com.jvm.instructions.base.BranchLogic;
import com.jvm.runTimeDateArea.model.Frame;

public class IFNONNULL extends BranchInstruction {
    public void execute(Frame frame){
        Object ref = frame.getOperandStack().popObj();
        if(ref != null){
            BranchLogic.branch(frame, this.getOffset());
        }
    }
}
