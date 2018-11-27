package com.jvm.instructions.stores;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

public class LSTORE_3 extends NoOperandsInstruction {
    public void execute(Frame frame){
        LstoreUtil.lstore(frame, 3);
    }
}
