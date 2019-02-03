package com.jvm.runTimeDateArea.server.impl;

import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.Slot;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.runTimeDateArea.server.OperandStackBussinessService;

/**
 * @Author: wangfa
 * @Date: 2018/11/13 14:55
 * @Description:
 */
public class OperandStackBussinessServiceImpl implements OperandStackBussinessService {

    @Override
    public void pushNull(Frame frame) {
        frame.getOperandStack().pushObj(null);
    }

    @Override
    public void pushDconst(Frame frame, double value) {
        frame.getOperandStack().pushDouble(value);
    }

    @Override
    public void pushFconst(Frame frame, float value) {
        frame.getOperandStack().pushFloat(value);
    }

    @Override
    public void pushIconst(Frame frame, int value) {
        frame.getOperandStack().pushInt(value);
    }

    @Override
    public void pushLConst(Frame frame, long value) {
        frame.getOperandStack().pushLong(value);
    }

    @Override
    public void pushOConst(Frame frame, SoObject value) {
        frame.getOperandStack().pushObj(value);
    }


    @Override
    public void popDStore(Frame frame, int index) {
        double value = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index,value);
    }

    @Override
    public void popFStore(Frame frame, int index) {
        float value = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index,value);
    }

    @Override
    public void popIStore(Frame frame, int index) {
        int value = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index,value);
    }

    @Override
    public void popLStore(Frame frame, int index) {
        long value = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index,value);
    }

    @Override
    public void popOStore(Frame frame, int index) {
        SoObject value = frame.getOperandStack().popObj();
        frame.getLocalVars().setObj(index,value);
    }

    @Override
    public void popSlot(Frame frame) {
        frame.getOperandStack().popSlot();
    }

    @Override
    public void popSlot2(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.popSlot();
        operandStack.popSlot();
    }

    @Override
    public void dupSlot(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot = operandStack.popSlot();
        operandStack.pushSlot(slot);
        operandStack.pushSlot(slot);

    }

    @Override
    public void dupSlot11(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);

    }

    @Override
    public void dupSlot12(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        Slot slot3 = operandStack.popSlot();
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot3);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }

    @Override
    public void dupSlot2(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }

    @Override
    public void dupSlot21(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        Slot slot3 = operandStack.popSlot();
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot3);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }

    @Override
    public void dupSlot22(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        Slot slot3 = operandStack.popSlot();
        Slot slot4 = operandStack.popSlot();
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot4);
        operandStack.pushSlot(slot3);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }

    @Override
    public void swap(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);
    }
}
