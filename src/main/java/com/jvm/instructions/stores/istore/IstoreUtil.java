package com.jvm.instructions.stores.istore;

import com.jvm.runTimeDateArea.model.Frame;

public class IstoreUtil {

    public static void istore(Frame frame, int index){
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index, val);
    }
}
