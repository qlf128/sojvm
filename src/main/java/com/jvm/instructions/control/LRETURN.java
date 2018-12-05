package com.jvm.instructions.control;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoThread;

//从方法中返回Long
public class LRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        SoThread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.getCurrentFrame();
        long value = currentFrame.getOperandStack().popLong();
        invokerFrame.getOperandStack().pushLong(value);
    }
}
