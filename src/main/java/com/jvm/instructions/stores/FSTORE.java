package com.jvm.instructions.stores;

        import com.jvm.instructions.Index8Instruction;
        import com.jvm.runTimeDateArea.model.Frame;

public class FSTORE extends Index8Instruction {

    public void execute(Frame frame){
        float value = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(this.getIndex(), value);
    }


}
