package com.jvm.instructions.references;

import com.jvm.instructions.BytecodeReader;
import com.jvm.instructions.Index16Instruction;
import com.jvm.instructions.Instructions;
import com.jvm.instructions.MethodInvokeLogic;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.soClassLoader.domain.*;
import com.jvm.soClassLoader.util.MethodLookupUtil;

/**
 * 与其他三条方法调用指令略有不同，invokeinterface指令的操作码后跟4字节，而非2字节
 * 前两字节的含义与其他指令相同，是个uint16运行时常量池索引
 * 第3字节的值是给方法传递参数需要的slot数，其含义和给Method结构体定义的argSlotCount字段相同，这个数可以根据方法描述符计算
 * 第4字节留给java虚拟机实现用，其值必须是0，改字节保证jvm可以向后兼容
 */
public class INVOKE_INTERFACE implements Instructions {
    private int index;
    //private int count;
    //private int zero;

    @Override
    public void fetchOperands(BytecodeReader byteCodeReader) {
        this.index = byteCodeReader.readUint16();
        byteCodeReader.readUint8();//count
        byteCodeReader.readUint8();//must be 0
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getSoClass().getConstantPool();
        InterfaceMethodRef methodRef = (InterfaceMethodRef) cp.getConstant(index);
        Method resolvedMethod = methodRef.resolvedInterfaceMethod();
        if (resolvedMethod.isStatic() || resolvedMethod.isPrivate()){
            throw new RuntimeException("IncompatibleClassChangeError");
        }
        //从操作数栈中弹出this引用
        SoObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount()-1);
        if (ref == null){
            throw new RuntimeException("NullPointerException");
        }
        if (!ClassHierarchy.isImplements(ref.getSoClass(), methodRef.resolvedClass())){
            //引用所指对象没有实现解析出的接口
            throw new RuntimeException("IncompatibleClassChangeError");
        }
        //查找要调用的方法并判断是否符合要求
        Method methodToBeInvoked =
                MethodLookupUtil.lookupMethodInClass(ref.getSoClass(), methodRef.getName(),methodRef.getDescriptor());
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()){
            throw new RuntimeException("AbstractMethodError");
        }
        if (! methodToBeInvoked.isPublic()){
            throw new RuntimeException("IllegalAccessError");
        }
        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }
}
