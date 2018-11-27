package com.jvm.instructions.extended;

import com.jvm.instructions.BytecodeReader;
import com.jvm.instructions.Index8Instruction;
import com.jvm.instructions.Instructions;
import com.jvm.instructions.loads.ILOAD;
import com.jvm.instructions.math.IINC;
import com.jvm.instructions.stores.LSTORE;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.util.DataTypeConvertUtil;

/**
 * FetchOperands()方法先从字节码中读取一字节的操作码，然后创建子指令实例，最后读取子指 令的操作数。
 * 因为没有实现ret指令，所以暂时调用panic()函数终止程序执行。
 * 加载指令和存储指令 都只有一个操作数，需要扩展成2字节.
 */
public class WIDE implements Instructions{
    private Instructions modifiedInstruction;

    public void fetchOperands(BytecodeReader byteCodeReader){
        int opcode = byteCodeReader.readUint8();
        byte[] opcodeArray = DataTypeConvertUtil.intToByte(opcode);
        byte opcodeLow8Bit = opcodeArray[0];

        Index8Instruction instructions;
        switch (opcodeLow8Bit) {
            case 0x15:
                instructions = new ILOAD();
                instructions.setIndex(byteCodeReader.readUint16());
                this.setModifiedInstruction(instructions);
            case 0x16:
                instructions = new LLOAD();
                instructions.setIndex(byteCodeReader.readUint16());
                this.setModifiedInstruction(instructions);
            case 0x17:
                instructions = new FLOAD();
                instructions.setIndex(byteCodeReader.readUint16());
                this.setModifiedInstruction(instructions);
            case 0x18:
                instructions = new DLOAD();
                instructions.setIndex(byteCodeReader.readUint16());
                this.setModifiedInstruction(instructions);
            case 0x19:
                instructions = new ALOAD();
                instructions.setIndex(byteCodeReader.readUint16());
                this.setModifiedInstruction(instructions);
            case 0x36:
                instructions = new ISTORE();
                instructions.setIndex(byteCodeReader.readUint16());
                this.setModifiedInstruction(instructions);
            case 0x37:
                instructions = new LSTORE();
                instructions.setIndex(byteCodeReader.readUint16());
                this.setModifiedInstruction(instructions);
            case 0x38:
                instructions = new FSTORE();
                instructions.setIndex(byteCodeReader.readUint16());
                this.setModifiedInstruction(instructions);
            case 0x39:
                instructions = new DSTORE();
                instructions.setIndex(byteCodeReader.readUint16());
                this.setModifiedInstruction(instructions);
            case 0x3a:
                instructions = new ASTORE();
                instructions.setIndex(byteCodeReader.readUint16());
                this.setModifiedInstruction(instructions);
            case 0x84:
                IINC inst = new IINC();
                inst.setIndex(byteCodeReader.readUint16());
                inst.setConsta(byteCodeReader.readInt16());
                this.setModifiedInstruction(inst);
            case 0xa9:
                panic("Unsupported opcode: 0xa9!");
        }
    }

    public void execute(Frame frame){
        this.modifiedInstruction.execute(frame);
    }

    public Instructions getModifiedInstruction() {
        return modifiedInstruction;
    }

    public void setModifiedInstruction(Instructions modifiedInstruction) {
        this.modifiedInstruction = modifiedInstruction;
    }
}
