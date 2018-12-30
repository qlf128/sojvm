package com.jvm.instructions.base;

import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.LocalVars;
import com.jvm.runTimeDateArea.model.Slot;
import com.jvm.runTimeDateArea.model.SoThread;
import com.jvm.soClassLoader.domain.Method;

/**
 * 定位到需要调用的方法后，java虚拟机要给此方法创建一个新帧并推入JVM栈顶，然后传递参数
 * 这个过程对于4条方法基本相同，此处以单独的文件实现此逻辑
 */
public class MethodInvokeLogic {
    public static void invokeMethod(Frame invokeFrame, Method method){
        //1.创建新帧并推入JVM栈
        SoThread thread = invokeFrame.getThread();
        Frame newFrame = thread.NewFrame(method);
        thread.pushFrame(newFrame);
        //2.传递参数
        int argSlotCount = method.getArgSlotCount();
        if (argSlotCount > 0){
            for (int i = argSlotCount - 1; i >= 0; i--){
                Slot slot = invokeFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }
        if (method.isNative()){
            //TODO 暂时跳过所有本地方法
            if (method.getName().equals("registerNatives")){
                thread.popFrame();
            } else {
                String info = String.format("native method:%s %s %s \n",
                        method.getSoClass().getName(),method.getName(),method.getDescriptor());
                throw new RuntimeException(info);
            }
        }
    }
}
