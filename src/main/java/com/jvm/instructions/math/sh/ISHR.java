package com.jvm.instructions.math.sh;

import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;

/**
 * 位移指令可以分为左移和右移两种，右移指令又可以分为算术右移(有符号右移)和逻辑右移
 (无符号右移)两种。算术右移和逻辑位移的区别仅在于符号位的扩展.

 * 左移指令.v1是要进行位移操作的变量，v2指出要移位多少比特
 * int变量只有32位，所以只取v2的前5个比特就 足够表示位移位数了
 * java:
 <<      :     左移运算符，num << 1,相当于num乘以2

 >>      :     右移运算符，num >> 1,相当于num除以2

 >>>    :     无符号右移，忽略符号位，空位都以0补齐

 */
public class ISHR extends NoOperandsInstruction {
    public void execute(Frame frame){
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        int s = v2 & 0x1f;
        int result = v1 >> s;
        operandStack.pushInt(result);
    }
}
