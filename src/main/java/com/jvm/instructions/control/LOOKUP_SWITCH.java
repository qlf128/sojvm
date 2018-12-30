package com.jvm.instructions.control;

import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.instruction.Instructions;
import com.jvm.instructions.base.BranchLogic;
import com.jvm.runTimeDateArea.model.Frame;

/**
 * matchOffsets有点像Map，它的key是case值，value是跳转偏移量。
 * Execute()方法先从操作数栈中 弹出一个int变量，然后用它查找matchOffsets，看是否能找到匹配的key。
 * 如果能，则按照value给出的偏 移量跳转，否则按照defaultOffset跳转。
 */
public class LOOKUP_SWITCH implements Instructions{
    private int defaultOffset;
    private int npairs;
    private int[] matchOffsets;

    public void fetchOperands(BytecodeReader reader){
        reader.skipPadding();
        this.defaultOffset = reader.readInt32();
        this.npairs = reader.readInt32();
        this.matchOffsets = reader.readInt32s(npairs * 2);
    }

    public void execute(Frame frame){
        int key = frame.getOperandStack().popInt();
        for(int i=0; i<npairs*2; i+=2){
            if(matchOffsets[i] == key){
                int offset = matchOffsets[i+1];
                BranchLogic.branch(frame, offset);
                return;
            }
        }
        BranchLogic.branch(frame, this.defaultOffset);
    }
}
