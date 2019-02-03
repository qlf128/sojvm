package com.jvm.instructions.comparisons.lcmp;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

/**
 * 把栈顶的两个long变量弹出，进行比较，然后把比较结果(int型0、1或-1)推入栈 顶
 */
public class LCMP extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        long v2 = stack.popLong();
        long v1 = stack.popLong();

        if(v1 > v2){
            stack.pushInt(1);
        }else if(v1 == v2){
            stack.pushInt(0);
        }else{
            stack.pushInt(-1);
        }
    }
}
