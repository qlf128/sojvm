package com.jvm.instructions.stores.fstore;

        import com.jvm.instructions.base.instruction.Index8Instruction;
        import com.jvm.runTimeDateArea.model.Frame;

public class FSTORE extends Index8Instruction {

    public void execute(Frame frame){
        float value = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(this.getIndex(), value);
    }


}
