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
public class XDASTORE extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {

        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        double value =  stack.popDouble();
        SoArrayObject objectArr = (SoArrayObject) stack.popObj();

        if(null==objectArr){
            System.err.println("java.lang.NullPointerException");
            System.exit(-1);
        }

        double[] doubles = objectArr.doubles();

        if (index < 0 || index>=doubles.length) {
            System.err.println("ArrayIndexOutOfBoundsException");
        }
        doubles[index] =value;

    }
}
