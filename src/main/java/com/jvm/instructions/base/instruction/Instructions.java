package com.jvm.instructions.base.instruction;

import com.jvm.instructions.base.BytecodeReader;
import com.jvm.runTimeDateArea.model.Frame;

public interface Instructions {

    public void fetchOperands(BytecodeReader byteCodeReader);

    public void execute(Frame frame);
}
