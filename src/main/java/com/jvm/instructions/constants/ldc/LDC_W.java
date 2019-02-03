package com.jvm.instructions.constants.ldc;

import com.jvm.instructions.base.instruction.Index16Instruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.runTimeDateArea.model.StringPool;
import com.jvm.soClassLoader.domain.ConstantPool;
import com.jvm.soClassLoader.domain.SoClass;
import com.jvm.util.NativeMethodUtil;

public class LDC_W extends Index16Instruction {
    public void execute(Frame frame){
        ldc(frame, this.getIndex());
    }

    private void ldc(Frame frame, int index){
        OperandStack stack = frame.getOperandStack();
        ConstantPool constantPool = frame.getMethod().getSoClass().getConstantPool();
        Object c = constantPool.getConstant(index);
        SoClass soClass = frame.getMethod().getSoClass();
        if(c instanceof Integer){
            stack.pushInt((int)c);
        }else if(c instanceof Float){
            stack.pushFloat((float)c);
        }else if(c instanceof String){
            //TODO 第八章
            SoObject soObject = StringPool.jString(soClass.getSoClassLoader(), (String) c);
            stack.pushObj(soObject);
        }else if(c instanceof Object){
            //TODO 第九章
        }else{
            NativeMethodUtil.panic("LDC error. no such object");
        }
    }
}
