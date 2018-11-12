package com.jvm.runTimeDateArea.model;

/**
 * @Author: wangfa
 * @Date: 2018/11/11 11:21
 * @Description: 运行时的线程类
 */
public class JavaThread {
    /**
     * PC 寄存器
     */
    private  int pcCount;

    /**
     * java 虚拟机栈
     */
    private  Stack stack;


    public JavaThread() {
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
     * 得到栈顶元素
     */
    public Frame getCurrentFrame(){
        return  stack.top();
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
}
