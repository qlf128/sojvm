package com.jvm.instructions.stores;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoArrayObject;

/**
 * @Author: wangfa
 * @Date: 2018/12/7 15:44
 * @Description:
 */
public class XFASTORE extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {

        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        float value =  stack.popFloat();
        SoArrayObject objectArr = (SoArrayObject) stack.popObj();

        if(null==objectArr){
            System.err.println("java.lang.NullPointerException");
            System.exit(-1);
        }

        float[] floats = objectArr.floats();

        if (index < 0 || index>=floats.length) {
            System.err.println("ArrayIndexOutOfBoundsException");
        }
        floats[index] =value;

    }
}
