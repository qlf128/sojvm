package com.jvm.instructions.loads;

import com.jvm.runTimeDateArea.model.Frame;

public class LloadUtil {
    /**
     * 从局部变量表获取long型变量，然后推入操作数栈顶
     * @param frame
     * @param index
     */
    public static void lload(Frame frame, int index){
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }
}
