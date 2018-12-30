package com.jvm.instructions.extended;

import com.jvm.instructions.base.instruction.BranchInstruction;
import com.jvm.instructions.base.BranchLogic;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 根据引用是否是null进行跳转，ifnull和ifnonnull指令把栈顶的引用弹出
 */
public class IFNULL extends BranchInstruction {

    public void execute(Frame frame){
        Object ref = frame.getOperandStack().popObj();
        if(ref == null){
            BranchLogic.branch(frame, this.getOffset());
        }
    }
}
