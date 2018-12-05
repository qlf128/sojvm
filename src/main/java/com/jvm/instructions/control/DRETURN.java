package com.jvm.instructions.control;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.runTimeDateArea.model.SoThread;
//从方法中返回double
public class DRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        SoThread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.getCurrentFrame();
        double value = currentFrame.getOperandStack().popDouble();
        invokerFrame.getOperandStack().pushDouble(value);
    }
}
