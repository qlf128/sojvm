package com.jvm.instructions.stack.dup;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.Slot;

/**
 * Duplicate the top one or two operand stack values
 */
public class DUP2 extends NoOperandsInstruction{
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();

        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();

        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
        stack.pushSlot(slot2);//为啥要push两遍
        stack.pushSlot(slot1);//为啥要push两遍
    }
}
