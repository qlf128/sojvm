package com.jvm.instructions.comparisons;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class FCMPL extends NoOperandsInstruction{

    public void execute(Frame frame){
        fcmp(frame, false);
    }

    private void fcmp(Frame frame, boolean gFlag){
        OperandStack stack = frame.getOperandStack();

        float v2 = stack.popFloat();
        float v1 = stack.popFloat();

        if(v1 > v2){
            stack.pushInt(1);
        }else if(v1 == v2){
            stack.pushInt(0);
        }else if(v1 < v2){
            stack.pushInt(-1);
        }else if gFlag{
            stack.pushInt(1);
        }else{
            stack.pushInt(-1);
        }
    }
}
