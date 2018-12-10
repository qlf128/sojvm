package com.jvm.instructions.stores;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class FSTORE_2 extends NoOperandsInstruction {
    public void execute(Frame frame){
        FstoreUtil.fstore(frame, 2);
    }
}
