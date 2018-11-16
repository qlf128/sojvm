package com.jvm.runTimeDateArea.server.impl;

import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.server.LocalVarsBussinessService;

/**
 * @Author: wangfa
 * @Date: 2018/11/13 16:07
 * @Description:
 */
public class LocalVarsBussinessServiceImpl implements LocalVarsBussinessService {

    @Override
    public void iLoad(Frame frame, int index) {
        int value = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(value);
    }

    @Override
    public void dLoad(Frame frame, int index) {
        double value = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(value);
    }

    @Override
    public void fLoad(Frame frame, int index) {
        float value = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(value);
    }

    @Override
    public void lLoad(Frame frame, int index) {
        long value = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(value);
    }

    @Override
    public void oLoad(Frame frame, int index) {
        Object value = frame.getLocalVars().getObj(index);
        frame.getOperandStack().pushObj(value);
    }
}
