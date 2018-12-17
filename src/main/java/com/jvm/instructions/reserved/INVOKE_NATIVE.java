package com.jvm.instructions.reserved;

import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.soClassLoader.domain.Method;
import com.jvm.util.NativeMethodUtil;

import java.lang.annotation.Native;

public class INVOKE_NATIVE extends NoOperandsInstruction{
    public void execute(Frame frame){
        Method method = frame.getMethod();
        String className = method.getSoClass().getName();
        String methodName = method.getName();
        String methodDescriptor = method.getDescriptor();

        nativeMethod = native.FindNativeMethod(className, methodName, methodDescriptor);

        if(nativeMethod == null){
            methodInfo = className + "." + methodName + methodDescriptor;
            NativeMethodUtil.panic("java.lang.UnsatisfiedLinkError: "+methodInfo);
        }

        nativeMethod(frame);
    }
}
