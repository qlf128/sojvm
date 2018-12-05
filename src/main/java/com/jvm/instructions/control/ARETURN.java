package com.jvm.instructions.control;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.runTimeDateArea.model.SoThread;
//从方法中返回引用
public class ARETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        SoThread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.getCurrentFrame();
        SoObject ref = currentFrame.getOperandStack().popObj();
        invokerFrame.getOperandStack().pushRef(ref);
    }
}
