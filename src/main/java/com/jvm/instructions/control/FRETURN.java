package com.jvm.instructions.control;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoThread;

public class FRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        SoThread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.getCurrentFrame();
        float value = currentFrame.getOperandStack().popFloat();
        invokerFrame.getOperandStack().pushFloat(value);
    }
}
