package com.jvm.instructions.loads.xaload;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoArrayObject;
import com.jvm.runTimeDateArea.model.SoObject;

/**
 * @Author: wangfa
 * @Date: 2018/12/7 15:04
 * @Description:  加载 对象的数组
 */
public class AALOAD extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {

        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        SoArrayObject object = (SoArrayObject) stack.popObj();
        if(null==object){
            System.err.println("java.lang.NullPointerException");
            System.exit(-1);
        }
        SoObject[] refs = object.refs();
        if (index < 0 || index>=refs.length) {
            System.err.println("ArrayIndexOutOfBoundsException");
        }
        stack.pushObj(refs[index]);

    }
}
