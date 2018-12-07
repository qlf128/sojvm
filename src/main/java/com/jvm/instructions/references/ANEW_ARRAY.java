package com.jvm.instructions.references;

import com.jvm.instructions.Index16Instruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoArrayObject;
import com.jvm.soClassLoader.domain.ClassRef;
import com.jvm.soClassLoader.domain.ConstantPool;
import com.jvm.soClassLoader.domain.SoArrayClass;
import com.jvm.soClassLoader.domain.SoClass;

/**
 * @Author: wangfa
 * @Date: 2018/12/7 11:51
 * @Description: anewarray指令用来创建引用类型数组
 */
public class ANEW_ARRAY  extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool pool = frame.getMethod().getSoClass().getConstantPool();
        ClassRef classRef = (ClassRef) pool.getConstant(this.getIndex());
        SoClass soClass = classRef.resolvedClass();
        OperandStack operandStack = frame.getOperandStack();
        int count = operandStack.popInt();
        if(count<0){
            System.err.println("创建数组失败");
            System.exit(-1);
        }
        SoArrayClass arrayClass = (SoArrayClass) soClass.arrayClass();
        SoArrayObject arrayObject = arrayClass.newArray(count);
        operandStack.pushObj(arrayObject);
    }
}
