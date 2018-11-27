package com.jvm.instructions.loads;

import com.jvm.runTimeDateArea.model.Frame;

public class IloadUtil {

    /**
     * 从局部变量表获取int型变量，然后推入操作数栈顶
     * @param frame
     * @param index
     */
    public static void iload(Frame frame, int index){
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }
}
