package com.jvm.instructions.stores.astore;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ASTORE_3 extends NoOperandsInstruction {
    public void execute(Frame frame){
        AstoreUtil.astore(frame, 3);
    }
}
