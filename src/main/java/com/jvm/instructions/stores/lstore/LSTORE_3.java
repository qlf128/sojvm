package com.jvm.instructions.stores.lstore;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class LSTORE_3 extends NoOperandsInstruction {
    public void execute(Frame frame){
        LstoreUtil.lstore(frame, 3);
    }
}
