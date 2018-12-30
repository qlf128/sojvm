package com.jvm.instructions.comparisons.if_acmp;

import com.jvm.instructions.base.instruction.BranchInstruction;
import com.jvm.instructions.base.BranchLogic;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

/**
 * 把栈顶的两个引用弹出，根据引用是否相同进行跳转
 */
public class IF_ACMPNE extends BranchInstruction{

    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        Object ref2 = stack.popObj();
        Object ref1 = stack.popObj();

        if(ref1 != ref2){
            BranchLogic.branch(frame, this.getOffset());
        }
    }
}
