package com.jvm.runTimeDateArea.model;

import com.jvm.soClassLoader.domain.Method;

/**
 * @Author: wangfa
 * @Date: 2018/11/11 11:21
 * @Description: 运行时的线程类
 */
public class SoThread {
    /**
     * PC 寄存器
     */
    private  int pcCount;

    /**
     * java 虚拟机栈
     */
    private  Stack stack;


    public SoThread() {
        stack = new Stack(1024);
    }


    /**
     * 入栈
     */
    public void pushFrame(Frame frame){
         stack.push(frame);
    }

    /**
     * 出栈
     */
    public Frame popFrame(){
        return  stack.pop();
    }

    /**
     *
     * 得到栈顶元素, 与getTopFrame含义相同，此处不写了
     */
    public Frame getCurrentFrame(){
        return  stack.top();
    }

    public boolean isStackEmpty(){
        return this.stack.isEmpty();
    }

    public Frame NewFrame(int maxLocals, int maxStack){
        return  new Frame(this,maxLocals,maxStack);
    }
    public Frame NewFrame(Method method){
        return  new Frame(this,method);
    }



    public int getPcCount() {
        return pcCount;
    }

    public void setPcCount(int pcCount) {
        this.pcCount = pcCount;
    }

    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public void clearStack(){
        this.stack.clear();
    }
}
