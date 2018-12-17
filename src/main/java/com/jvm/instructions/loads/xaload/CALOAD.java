package com.jvm.instructions.loads.xaload;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoArrayObject;

/**
 * @Author: wangfa
 * @Date: 2018/12/7 15:21
 * @Description: 加载 char 数组
 */
public class CALOAD extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {

        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        SoArrayObject object = (SoArrayObject) stack.popObj();

        if(null==object){
            System.err.println("java.lang.NullPointerException");
            System.exit(-1);
        }
        char[] chars = object.chars();
        if (index < 0 || index>=chars.length) {
            System.err.println("ArrayIndexOutOfBoundsException");
        }
        stack.pushInt(chars[index]);

    }
}
