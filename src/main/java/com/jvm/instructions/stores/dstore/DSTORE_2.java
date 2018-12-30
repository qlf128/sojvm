package com.jvm.instructions.stores.dstore;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class DSTORE_2 extends NoOperandsInstruction {
    public void execute(Frame frame){
        DstoreUtil.dstore(frame, 2);
    }
}
