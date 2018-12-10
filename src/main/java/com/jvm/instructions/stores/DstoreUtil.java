package com.jvm.instructions.stores;

import com.jvm.runTimeDateArea.model.Frame;

public class DstoreUtil {
    public static void dstore(Frame frame, int index){
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }
}
