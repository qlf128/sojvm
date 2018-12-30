package com.jvm.instructions.references.monitor;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.util.NativeMethodUtil;

public class MONITOR_ENTER extends NoOperandsInstruction{
    public void execute(Frame frame){
        Object ref = frame.getOperandStack().popObj();
        if(ref == null){
            NativeMethodUtil.panic("java.lang.NullPointerException");
        }
    }
}
