package com.jvm.instructions.comparisons.dcmp;

import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

public class DCMP {
    public static void dcmp(Frame frame, boolean gFlag){
        OperandStack stack = frame.getOperandStack();

        double v2 = stack.popDouble();
        double v1 = stack.popDouble();

        if(v1 > v2){
            stack.pushInt(1);
        }else if(v1 == v2){
            stack.pushInt(0);
        }else if(v1 < v2){
            stack.pushInt(-1);
        }else if (gFlag) {
            stack.pushInt(1);
        }else{
            stack.pushInt(-1);
        }
    }
}
