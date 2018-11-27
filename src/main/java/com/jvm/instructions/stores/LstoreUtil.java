package com.jvm.instructions.stores;

import com.jvm.runTimeDateArea.model.Frame;

public class LstoreUtil {

    public static void lstore(Frame frame, int index){
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index, val);
    }
}
