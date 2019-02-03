package com.jvm.instructions.control.returns;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoThread;

public class IRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        SoThread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.getCurrentFrame();
        int value = currentFrame.getOperandStack().popInt();
        invokerFrame.getOperandStack().pushInt(value);
    }
}
