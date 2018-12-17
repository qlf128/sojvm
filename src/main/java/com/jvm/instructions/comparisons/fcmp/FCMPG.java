package com.jvm.instructions.comparisons.fcmp;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

/**
 * 由于浮 点数计算有可能产生NaN(Not a Number)值，所以比较两个浮点数时，除了大于、等于、小于之外， 还有第4种结果:无法比较。
 * fcmpg和fcmpl指令的区别就在于对第4种结果的定义。
 */
public class FCMPG extends NoOperandsInstruction{

    public void execute(Frame frame){
        fcmp(frame, true);
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
        }else if (gFlag) {
            stack.pushInt(1);
        }else{
            stack.pushInt(-1);
        }
    }
}
