package com.jvm.instructions.stores.astore;

import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoObject;

public class AstoreUtil {
    public static void astore(Frame frame, int index){
        SoObject val = frame.getOperandStack().popObj();
        frame.getLocalVars().setObj(index, val);
    }
}
