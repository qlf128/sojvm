package com.jvm.heap;

import com.jvm.soClassLoader.domain.ClassRef;

public class ExceptionHandler {
    private int startPc;
    private int endPc;
    private int handlerPc;
    private ClassRef catchType;

    public ExceptionHandler(int startPc, int endPc, int handlerPc, ClassRef catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
    }

    public int getStartPc() {
        return startPc;
    }

    public void setStartPc(int startPc) {
        this.startPc = startPc;
    }

    public int getEndPc() {
        return endPc;
    }

    public void setEndPc(int endPc) {
        this.endPc = endPc;
    }

    public int getHandlerPc() {
        return handlerPc;
    }

    public void setHandlerPc(int handlerPc) {
        this.handlerPc = handlerPc;
    }

    public ClassRef getCatchType() {
        return catchType;
    }

    public void setCatchType(ClassRef catchType) {
        this.catchType = catchType;
    }
}
