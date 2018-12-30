package com.jvm.instructions.comparisons.if_icmp;

import com.jvm.instructions.base.instruction.BranchInstruction;
import com.jvm.instructions.base.BranchLogic;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class IF_ICMPEQ extends BranchInstruction{

    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        int val2 = stack.popInt();
        int val1 = stack.popInt();

        if(val1 != val2){
            BranchLogic.branch(frame, this.getOffset());
        }
    }
}
