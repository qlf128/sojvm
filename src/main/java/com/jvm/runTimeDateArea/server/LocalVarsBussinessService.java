package com.jvm.runTimeDateArea.server;

import com.jvm.runTimeDateArea.model.Frame;

/**
 * @Author: wangfa
 * @Date: 2018/11/13 16:07
 * @Description: 局部变量表 服务接口
 */
public interface LocalVarsBussinessService {
    /**
     * 加载指令 从局部变量中获取变量，然后推入到操作数栈顶
     */
    /**
     *  操作int 类型变量
     */
    void iLoad(Frame frame ,int index);


    /**
     *  操作double 类型变量
     */
    void dLoad(Frame frame ,int index);


    /**
     *  操作float 类型变量
     */
    void fLoad(Frame frame ,int index);


    /**
     *  操作long 类型变量
     */
    void lLoad(Frame frame ,int index);


    /**
     *  操作obj 类型变量
     */
    void oLoad(Frame frame ,int index);

}

