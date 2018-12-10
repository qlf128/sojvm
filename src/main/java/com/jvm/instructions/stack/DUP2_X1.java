package com.jvm.instructions.stack;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.Slot;

/**
 * Duplicate the top one or two operand stack values
 * and insert two or three values down
 */
public class DUP2_X1 extends NoOperandsInstruction {
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();

        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        Slot slot3 = stack.popSlot();

        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
        stack.pushSlot(slot3);
        stack.pushSlot(slot2);//为啥要push两遍
        stack.pushSlot(slot1);//为啥要push两遍
    }
}
