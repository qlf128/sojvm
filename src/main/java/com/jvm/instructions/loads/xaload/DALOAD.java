package com.jvm.instructions.loads.xaload;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoArrayObject;

/**
 * @Author: wangfa
 * @Date: 2018/12/7 15:24
 * @Description: 加载 double 的数组
 */
public class DALOAD extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {

        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        SoArrayObject object = (SoArrayObject) stack.popObj();

        if(null==object){
            System.err.println("java.lang.NullPointerException");
            System.exit(-1);
        }
        double[] doubles = object.doubles();
        if (index < 0 || index>=doubles.length) {
            System.err.println("ArrayIndexOutOfBoundsException");
        }
        stack.pushDouble(doubles[index]);

    }
}
