package com.jvm.instructions.stores.dstore;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class DSTORE_1 extends NoOperandsInstruction {
    public void execute(Frame frame){
        DstoreUtil.dstore(frame, 1);
    }
}
