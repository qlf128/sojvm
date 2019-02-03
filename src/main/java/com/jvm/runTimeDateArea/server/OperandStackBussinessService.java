package com.jvm.runTimeDateArea.server;

import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoObject;

/**
 * @Author: wangfa
 * @Date: 2018/11/13 14:53
 * @Description: 操作数栈接口服务
 */
public interface OperandStackBussinessService {

    /** 常量指令 把常量推入到操作数栈顶
     * 常量来自三个地方 隐含在操作码里，操作数和运行时常量池。
     * const系列指令 把隐含在操作码里的常量值推入操作数栈顶
     */

    /**
     *  往操作数栈中添加null 引用
     */
    void pushNull(Frame frame);

    /**
     * 往操作数栈中添加 double
     */
    void pushDconst(Frame frame,double value);

    /**
     * 往操作数栈中添加 float
     */
    void pushFconst(Frame frame,float value);

    /**
     * 往操作数栈中添加 int
     */
    void pushIconst(Frame frame,int value);

    /**
     * 往操作数栈中添加 long
     */
    void pushLConst(Frame frame,long value);

    /**
     * 往操作数栈中添加 obj
     */
    void pushOConst(Frame frame,SoObject value);




    /**
     *  存储指令  把变量从操作数栈弹出 放入局部变量表中
     */

    /**
     *  弹出double类型
     */
    void popDStore(Frame frame, int index);

    /**
     *  弹出float类型
     */
    void popFStore(Frame frame, int index);

    /**
     *  弹出int类型
     */
    void popIStore(Frame frame, int index);

    /**
     *  弹出long类型
     */
    void popLStore(Frame frame, int index);

    /**
     *  弹出obj类型
     */
    void popOStore(Frame frame, int index);


    /**
     * 栈指令   栈指令 直接对操作数进行操作
     *          pop 弹出栈顶
     *          dup 复制栈顶变量
     *          swap 交换栈顶的两个变量
     */


    /**
     * popSlot 用于操作占一个操作数栈位置的变量 int float
     * popSlot2 用于操作占两个操作数栈位置的变量 double long
     */
    void popSlot(Frame frame);
    void popSlot2(Frame frame);

    /**
     * 复制栈顶一个或两个数值并将复制值或双份的复制值重新压入栈顶
     */

    void dupSlot(Frame frame);
    void dupSlot11(Frame frame);
    void dupSlot12(Frame frame);

    void dupSlot2(Frame frame);
    void dupSlot21(Frame frame);
    void dupSlot22(Frame frame);


    /**
     * 交换帧栈的两个变量
     */
    void swap(Frame frame);


}
