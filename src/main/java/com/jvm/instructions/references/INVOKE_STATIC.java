package com.jvm.instructions.references;

import com.jvm.instructions.base.ClassInitLogic;
import com.jvm.instructions.base.instruction.Index16Instruction;
import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.soClassLoader.domain.ConstantPool;
import com.jvm.soClassLoader.domain.Method;
import com.jvm.soClassLoader.domain.MethodRef;
import com.jvm.soClassLoader.domain.SoClass;

public class INVOKE_STATIC extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getSoClass().getConstantPool();
        MethodRef methodRef = (MethodRef) cp.getConstant(super.getIndex());
        Method resolvedMethod = methodRef.resolvedMethod();
        if (! resolvedMethod.isStatic()){
            throw new RuntimeException("IncompatibleClassChangeError");
        }
        SoClass soClass = resolvedMethod.getSoClass();
        if (! soClass.isInitStarted()){
            frame.revertNextPC();
            //书中解释：类初始化方法只能由Java虚拟机调用，不能使用invokestatic指令调用
            ClassInitLogic.initClass(frame.getThread(), soClass);
            return;
        }
        MethodInvokeLogic.invokeMethod(frame, resolvedMethod);
    }
}
