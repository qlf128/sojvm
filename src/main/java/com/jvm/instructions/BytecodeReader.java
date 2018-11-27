package com.jvm.instructions;

import com.jvm.util.DataTypeConvertUtil;

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
        //TODO:把byte的uint转换成int

        pc++;
        return i;
    }

    public int readInt8(){
        byte i = code[pc];
        pc++;
        return i;
    }

    public int readUint16(){
        byte byte1 = code[pc];
        pc++;
        byte byte2 = code[pc];
        pc++;
        return (byte1 << 8) | byte2;
    }

    public int readInt16(){
        return readUint16();
    }

    public int readUint32(){
        int byte1 = code[pc];
        pc++;
        int byte2 = code[pc];
        pc++;
        int byte3 = code[pc];
        pc++;
        int byte4 = code[pc];
        pc++;

        return (byte1 << 24) | (byte2 << 16) | (byte3 << 8) | byte4;
    }

    public int readInt32(){
        return readUint32();
    }

    public void SkipPadding(){

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
