package com.jvm.instructions.stores;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ASTORE_1 extends NoOperandsInstruction {
    public void execute(Frame frame){
        AstoreUtil.astore(frame, 1);
    }
}
