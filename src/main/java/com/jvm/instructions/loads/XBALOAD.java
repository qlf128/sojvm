package com.jvm.instructions.loads;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoArrayObject;
import com.jvm.runTimeDateArea.model.SoObject;

/**
 * @Author: wangfa
 * @Date: 2018/12/7 15:17
 * @Description: 加载 byte 或者 boolean 类型的数组
 */
public class XBALOAD extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {

        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        SoArrayObject object = (SoArrayObject) stack.popObj();

        if(null==object){
            System.err.println("java.lang.NullPointerException");
            System.exit(-1);
        }
        byte[] bytes = object.bytes();
        if (index < 0 || index>=bytes.length) {
            System.err.println("ArrayIndexOutOfBoundsException");
        }
        stack.pushInt(bytes[index]);

    }
}