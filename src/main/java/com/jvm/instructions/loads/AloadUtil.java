package com.jvm.instructions.loads;

import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoObject;

public class AloadUtil {
    /**
     * 从局部变量表获取引用型变量，然后推入操作数栈顶
     * @param frame
     * @param index
     */
    public static void aload(Frame frame, int index){
        SoObject val = frame.getLocalVars().getObj(index);
        frame.getOperandStack().pushObj(val);
    }
}
