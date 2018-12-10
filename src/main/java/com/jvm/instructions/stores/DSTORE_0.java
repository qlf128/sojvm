package com.jvm.instructions.stores;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class DSTORE_0 extends NoOperandsInstruction {
    public void execute(Frame frame){
        DstoreUtil.dstore(frame, 0);
    }
}
