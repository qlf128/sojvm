package com.jvm.instructions.stack;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.Slot;

/**
 * Duplicate the top operand stack value and insert two values down
 */
public class DUP_X1 extends NoOperandsInstruction {
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();

        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();

        stack.pushSlot(slot1);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
