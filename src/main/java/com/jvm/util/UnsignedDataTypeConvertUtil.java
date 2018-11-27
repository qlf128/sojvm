package com.jvm.util;

public class UnsignedDataTypeConvertUtil {

    public static int getUnsignedByte(byte data){
        return data & 0xFF;
    }

    public static int getUnsignedShort(short data){
        return data & 0x0FFFF;
    }

    public static long getUnsignedInt(int data){
        return data & 0x0FFFFFFFFL;
    }

    public static void main(String[] args){
        int a = -3;
        System.out.println(getUnsignedInt(a));
    }
}
