package com.jvm.instructions.control.returns;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * return用于没有返回值的情况
 */
public class RETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getThread().popFrame();
    }
}