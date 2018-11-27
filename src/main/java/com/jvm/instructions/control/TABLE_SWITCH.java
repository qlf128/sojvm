package com.jvm.instructions.control;

import com.jvm.instructions.BytecodeReader;
import com.jvm.instructions.Instructions;
import com.jvm.instructions.base.Base;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * defaultOffset对应默认情况下执行跳转所需的字节码偏移量;low和high记录case的取值范围;
 * jumpOffsets是一个索引表，里面存放high-low+1个int值，对应各种case情况下，执行跳转所需的字节码 偏移量。
 */
public class TABLE_SWITCH implements Instructions {
    private int defaultOffset;
    private int low;
    private int high;
    private int[] jumpOffsets;

    public void fetchOperands(BytecodeReader reader){
        reader.skipPadding();
        this.setDefaultOffset(reader.readInt32());
        this.setLow(reader.readInt32());
        this.setHigh(reader.readInt32());
        int jumpOffsetsCount = this.getHigh() - this.getLow() + 1;
        this.setJumpOffsets(reader.readInt32s(jumpOffsetsCount));

    }

    /**
     * 先从操作数栈中弹出一个int变量，然后看它是否在low和high给定的范围之内。如 果在，则从jumpOffsets表中查出偏移量进行跳转，否则按照defaultOffset跳转。
     * @param frame
     */
    public void execute(Frame frame){
        int index = frame.getOperandStack().popInt();
        int offset;

        if(index >= this.getLow() && index <= this.getHigh()){
            offset = jumpOffsets[index-low];
        }else{
            offset = defaultOffset;
        }
        Base.branch(frame, offset);
    }


    public int getDefaultOffset() {
        return defaultOffset;
    }

    public void setDefaultOffset(int defaultOffset) {
        this.defaultOffset = defaultOffset;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int[] getJumpOffsets() {
        return jumpOffsets;
    }

    public void setJumpOffsets(int[] jumpOffsets) {
        this.jumpOffsets = jumpOffsets;
    }
}
