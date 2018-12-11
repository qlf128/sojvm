package com.jvm.instructions.comparisons.if_icmp;

import com.jvm.instructions.BranchInstruction;
import com.jvm.instructions.base.Base;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class IF_ICMPNE extends BranchInstruction {
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        int val2 = stack.popInt();
        int val1 = stack.popInt();

        if(val1 == val2){
            Base.branch(frame, this.getOffset());
        }
    }
}
