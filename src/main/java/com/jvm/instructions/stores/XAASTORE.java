package com.jvm.instructions.stores;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoArrayObject;
import com.jvm.runTimeDateArea.model.SoObject;

/**
 * @Author: wangfa
 * @Date: 2018/12/7 15:35
 * @Description: 存储 对象的数组
 */
public class XAASTORE  extends NoOperandsInstruction{
    @Override
    public void execute(Frame frame) {

        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        SoObject object =  stack.popObj();
        SoArrayObject objectArr = (SoArrayObject) stack.popObj();

        if(null==objectArr){
            System.err.println("java.lang.NullPointerException");
            System.exit(-1);
        }
        SoObject[] refs = objectArr.refs();
        if (index < 0 || index>=refs.length) {
            System.err.println("ArrayIndexOutOfBoundsException");
        }
        refs[index] = object;

    }
}
