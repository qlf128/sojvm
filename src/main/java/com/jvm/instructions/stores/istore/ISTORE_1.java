package com.jvm.instructions.stores.istore;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ISTORE_1 extends NoOperandsInstruction {
    public void execute(Frame frame){
        IstoreUtil.istore(frame, 1);
    }
}
