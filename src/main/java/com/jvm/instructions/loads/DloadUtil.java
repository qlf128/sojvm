package com.jvm.instructions.loads;

import com.jvm.runTimeDateArea.model.Frame;

public class DloadUtil {
    /**
     * 从局部变量表获取double型变量，然后推入操作数栈顶
     * @param frame
     * @param index
     */
    public static void lload(Frame frame, int index){
        double val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }
}
