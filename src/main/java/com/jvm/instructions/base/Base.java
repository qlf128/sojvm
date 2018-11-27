package com.jvm.instructions.base;

import com.jvm.runTimeDateArea.model.Frame;

public class Base {

    /**
     * 跳转
     * @param frame
     * @param offset
     */
    public static void branch(Frame frame, int offset){
        int pc = frame.getThread().getPcCount();
        int nextPc = pc + offset;
        frame.setNextPC(nextPc);
    }
}
