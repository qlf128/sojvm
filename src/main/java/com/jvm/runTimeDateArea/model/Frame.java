package com.jvm.runTimeDateArea.model;

/**
 * @Author: wangfa
 * @Date: 2018/11/11 11:51
 * @Description:  帧栈
 */
public class Frame {

    private Frame lower;

    /**
     * 局部变量表 大小由编译期决定
     */
    private LocalVars localVars;

    /**
     * 操作数栈 大小由编译期决定
     */
    private  OperandStack operandStack;


    /**
     * 局部变量表大小和操作数栈深度由编译期确定
     * @param maxLocals
     * @param maxStack
     */
    public Frame(int maxLocals,int maxStack) {
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }

    public Frame getLower() {
        return lower;
    }

    public void setLower(Frame lower) {
        this.lower = lower;
    }
}
