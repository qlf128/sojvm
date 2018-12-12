package com.jvm.runTimeDateArea.model;

/**
 * @Author: wangfa
 * @Date: 2018/11/11 14:04
 * @Description: java 虚拟机栈  所使用的内存不需要连续
 */
public class Stack {

    /**
     * 虚拟机栈最大值
     */
    private int maxSize;
    /**
     * 当前虚拟机栈大小
     */
    private int size;

    /**
     * 虚拟机栈栈顶的那一个帧栈
     */
    private Frame topFrame;


    public Stack(int maxSize) {
        this.maxSize = maxSize;
    }

    // 入栈 把帧栈推入栈顶
    public void push(Frame frame) {

        if (size >= maxSize) {
            System.out.println("虚拟机栈已满...");
            System.exit(-1);
        }
        if(null!=topFrame){
            frame.setLower(topFrame);
        }
        topFrame=frame;
        size++;
    }

    // 出栈 pop
    public Frame pop() {
        if (null == topFrame){
            System.out.println("虚拟机栈为空...");
            System.exit(-1);
        }
        Frame top = topFrame;
        topFrame = top.getLower();
        size--;
        return top;
    }

    // 得到栈顶元素
    public Frame top(){
        if (null == topFrame){
            System.out.println("虚拟机栈为空...");
            System.exit(-1);
        }
        return topFrame;
    }

    public boolean isEmpty(){
        return topFrame == null;
    }

    public Stack clear(){
        while(!isEmpty()){
            pop();
        }
        return this;
    }
}
