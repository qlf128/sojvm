package com.jvm.util;

/**
 * 无符号工具类
 */
public class UnsignedDataTypeConvertUtil {

    /**
     * 无符号byte转换成int
     * 转换原理：
     * data是byte，0xFF是int
     * 在&操作之前，data会转换成int型，如果是负数，则前面的24位会补1.如果是正数，则前面的24位会补0.
     * 然后data跟0xFF(0x000000FF)相与，如果前24为0，则不影响。如果前24位为1，则会被与成0.得到的就是data的无符号值。
     * @param data
     * @return
     */
    public static int getUnsignedByte(byte data){
        return data & 0xFF;
    }

    /**
     * 无符号short转换成int
     * @param data
     * @return
     */
    public static int getUnsignedShort(short data){
        return data & 0xFFFF;
    }

    /**
     * 无符号int转换成long
     * @param data
     * @return
     */
    public static long getUnsignedInt(int data){
        return data & 0x0FFFFFFFFL;
    }

    public static void main(String[] args){
        short a = -3;
        System.out.println(getUnsignedShort(a));
    }
}
