package com.jvm.instructions;

import com.jvm.runTimeDateArea.model.Frame;

public interface Instructions {

    public void fetchOperands(BytecodeReader byteCodeReader);

    public void execute(Frame frame);
}
