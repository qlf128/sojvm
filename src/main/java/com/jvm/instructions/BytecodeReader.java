package com.jvm.instructions;

import com.jvm.util.UnsignedDataTypeConvertUtil;

public class BytecodeReader {
    private byte[] code;//代码段
    private int pc;//读取到哪个字段

    public BytecodeReader reset(byte[] code, int pc){
        this.code = code;
        this.pc = pc;

        return this;
    }

    public int readUint8(){
        byte i = code[pc];
        //把byte的uint转换成int
        int result = UnsignedDataTypeConvertUtil.getUnsignedByte(i);
        pc++;
        return result;
    }

    public int readInt8(){
        byte i = code[pc];
        pc++;
        return i;
    }

    public int readUint16(){
        byte byte1 = code[pc];
        int ubyte1 = UnsignedDataTypeConvertUtil.getUnsignedShort(byte1);
        pc++;

        byte byte2 = code[pc];
        int ubyte2 = UnsignedDataTypeConvertUtil.getUnsignedShort(byte2);
        pc++;

        return (ubyte1 << 8) | ubyte2;
    }

    public int readInt16(){
        short s = (short)readUint16();
        return (int)s;
    }

    public long readUint32(){
        byte byte1 = code[pc];
        int ubyte1 = UnsignedDataTypeConvertUtil.getUnsignedShort(byte1);
        pc++;

        byte byte2 = code[pc];
        int ubyte2 = UnsignedDataTypeConvertUtil.getUnsignedShort(byte1);
        pc++;

        byte byte3 = code[pc];
        int ubyte3 = UnsignedDataTypeConvertUtil.getUnsignedShort(byte3);
        pc++;

        byte byte4 = code[pc];
        int ubyte4 = UnsignedDataTypeConvertUtil.getUnsignedShort(byte4);
        pc++;

        int result = (ubyte1 << 24) | (ubyte2 << 16) | (ubyte3 << 8) | ubyte4;
        return UnsignedDataTypeConvertUtil.getUnsignedInt(result);
    }

    public int readInt32(){
        byte byte1 = code[pc];
        int ubyte1 = UnsignedDataTypeConvertUtil.getUnsignedShort(byte1);
        pc++;

        byte byte2 = code[pc];
        int ubyte2 = UnsignedDataTypeConvertUtil.getUnsignedShort(byte1);
        pc++;

        byte byte3 = code[pc];
        int ubyte3 = UnsignedDataTypeConvertUtil.getUnsignedShort(byte3);
        pc++;

        byte byte4 = code[pc];
        int ubyte4 = UnsignedDataTypeConvertUtil.getUnsignedShort(byte4);
        pc++;

        int result = (ubyte1 << 24) | (ubyte2 << 16) | (ubyte3 << 8) | ubyte4;

        return result;
    }

    public int[] readInt32s(int n){
        int[] ints = new int[n];
        for(int i=0; i<ints.length;i++){
            ints[i] = readInt32();
        }

        return ints;
    }

    public void skipPadding(){
        for(;this.getPc() % 4 != 0;){
            readUint8();
        }
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }
}
