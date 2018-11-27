package com.jvm.instructions.base;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

/**
 * 把栈顶变量（占用一个操作数栈位置）弹出
 */
public class POP extends NoOperandsInstruction{

    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        stack.popSlot();
    }
}
