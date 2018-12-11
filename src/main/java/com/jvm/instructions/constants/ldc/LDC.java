package com.jvm.instructions.constants.ldc;

import com.jvm.instructions.Index8Instruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.Stack;
import com.jvm.soClassLoader.domain.ConstantPool;
import com.jvm.util.NativeMethodUtil;

public class LDC extends Index8Instruction{
    public void execute(Frame frame){
        ldc(frame, this.getIndex());
    }

    private void ldc(Frame frame, int index){
        OperandStack stack = frame.getOperandStack();
        ConstantPool constantPool = frame.getMethod().getSoClass().getConstantPool();
        Object c = constantPool.getConstant(index);

        if(c instanceof Integer){
            stack.pushInt((int)c);
        }else if(c instanceof Float){
            stack.pushFloat((float)c);
        }else if(c instanceof String){
            //TODO 第八章
        }else if(c instanceof Object){
            //TODO 第九章
        }else{
            NativeMethodUtil.panic("LDC error. no such object");
        }
    }

}
