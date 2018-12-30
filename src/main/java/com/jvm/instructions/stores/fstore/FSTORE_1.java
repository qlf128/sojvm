package com.jvm.instructions.stores.fstore;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class FSTORE_1 extends NoOperandsInstruction {
    public void execute(Frame frame){
        FstoreUtil.fstore(frame, 1);
    }
}
