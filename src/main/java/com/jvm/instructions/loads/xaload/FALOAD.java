package com.jvm.instructions.loads.xaload;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoArrayObject;

/**
 * @Author: wangfa
 * @Date: 2018/12/7 15:24
 * @Description: 加载 float 的数组
 */
public class FALOAD extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {

        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        SoArrayObject object = (SoArrayObject) stack.popObj();

        if(null==object){
            System.err.println("java.lang.NullPointerException");
            System.exit(-1);
        }

        float[] floats = object.floats();
        if (index < 0 || index>=floats.length) {
            System.err.println("ArrayIndexOutOfBoundsException");
        }
        stack.pushFloat(floats[index]);

    }
}
