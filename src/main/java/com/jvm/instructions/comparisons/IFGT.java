package com.jvm.instructions.comparisons;

import com.jvm.instructions.BranchInstruction;
import com.jvm.instructions.base.Base;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * 把操作数栈顶的int变量弹出，然后跟0进行比较，满足条件则跳转
 *
 ·ifeq:x==0
 ·ifne:x!=0
 ·iflt:x<0
 ·ifle:x<=0
 ·ifgt:x>0
 ·ifge:x>=0
 *
 */
public class IFGT extends BranchInstruction{
    public void execute(Frame frame){
        int val = frame.getOperandStack().popInt();
        if(val > 0){
            Base.branch(frame, this.getOffset());
        }
    }
}
