package com.jvm.instructions.references;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoArrayObject;
import com.jvm.runTimeDateArea.model.SoObject;

/**
 * @Author: wangfa
 * @Date: 2018/12/7 14:39
 * @Description: arraylength指令用于获取数组长度
 */
public class ARRAY_LENGTH extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
         SoArrayObject soObject = (SoArrayObject) operandStack.popObj();
        if(null==soObject){
            System.err.println("java.lang.NullPointerException");
        }
        int length = soObject.arrayLength();
        operandStack.pushInt(length);
    }
}
