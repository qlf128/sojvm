package com.jvm.instructions.stores.fstore;

import com.jvm.runTimeDateArea.model.Frame;

public class FstoreUtil {
    public static void fstore(Frame frame, int index){
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index, val);
    }
}
