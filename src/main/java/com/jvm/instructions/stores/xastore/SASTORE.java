package com.jvm.instructions.stores.xastore;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoArrayObject;

/**
 * @Author: wangfa
 * @Date: 2018/12/7 15:44
 * @Description:
 */
public class SASTORE extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {

        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        int value =  stack.popInt();
        SoArrayObject objectArr = (SoArrayObject) stack.popObj();

        if(null==objectArr){
            System.err.println("java.lang.NullPointerException");
            System.exit(-1);
        }

        short[] shorts = objectArr.shorts();

        if (index < 0 || index>=shorts.length) {
            System.err.println("ArrayIndexOutOfBoundsException");
        }
        shorts[index] =(short) value;

    }
}
