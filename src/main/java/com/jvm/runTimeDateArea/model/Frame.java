package com.jvm.runTimeDateArea.model;

import com.jvm.soClassLoader.domain.Method;

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
     *  线程  实现跳转指令专用字段
     */
    private SoThread thread;

    /**
     *  nextPc 实现跳转指令专用字段
     */

    private int nextPC;


    /**
     * method 实现new指令的专有字段
     */
    private Method method;

    /**
     * 局部变量表大小和操作数栈深度由编译期确定
     * @param maxLocals
     * @param maxStack
     */
    public Frame(SoThread thread, int maxLocals, int maxStack) {
        this.thread =thread;
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }

    public Frame(SoThread thread, Method method) {
        this.thread = thread;
        this.method = method;
        localVars = new LocalVars(method.getMaxLocals());
        operandStack = new OperandStack(method.getMaxStack());
    }
    //修改nextPC使其重新指向当前指令
    public void revertNextPC(){
        this.nextPC = this.thread.getPcCount();
    }

    public Frame getLower() {
        return lower;
    }

    public void setLower(Frame lower) {
        this.lower = lower;
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public void setLocalVars(LocalVars localVars) {
        this.localVars = localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public void setOperandStack(OperandStack operandStack) {
        this.operandStack = operandStack;
    }


    public SoThread getThread() {
        return thread;
    }

    public void setThread(SoThread thread) {
        this.thread = thread;
    }

    public int getNextPC() {
        return nextPC;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
