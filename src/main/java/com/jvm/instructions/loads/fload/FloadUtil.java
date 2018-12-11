package com.jvm.instructions.loads.fload;

import com.jvm.runTimeDateArea.model.Frame;

public class FloadUtil {
    /**
     * 从局部变量表获取float型变量，然后推入操作数栈顶
     * @param frame
     * @param index
     */
    public static void lload(Frame frame, int index){
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }
}
