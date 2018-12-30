package com.jvm.instructions.stores.istore;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class ISTORE_0 extends NoOperandsInstruction {
    public void execute(Frame frame){
        IstoreUtil.istore(frame, 0);
    }
}
