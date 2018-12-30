package com.jvm.instructions.stack.pop;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class POP2 extends NoOperandsInstruction {
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        stack.popSlot();
        stack.popSlot();
    }
}
