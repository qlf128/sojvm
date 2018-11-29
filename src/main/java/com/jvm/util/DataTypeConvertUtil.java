package com.jvm.util;

/**
 * int和其他基本类型转换类
 */
public class DataTypeConvertUtil {
    /**
     * byte转int
     * @param b
     * @return
     */
    public static int byteToInt(byte b){
        return b & 0xFFFFFFFF;
    }


    /**
     * byte转int
     * big-endian 高位在前字节序
     * int = byte1 << 24 | byte2 << 16 | byte3 << 8 | byte4
     * @param byte1-最高8位
     * @param byte2
     * @param byte3
     * @param byte4-最低8位
     * @return
     */
    public static int byteArrayToInt(byte byte1, byte byte2, byte byte3, byte byte4){
        return byte1 << 24 | byte2 << 16 | byte3 << 8 | byte4;
    }

    /**
     * byte转short
     * big-endian 高位在前字节序
     * short = byte1 << 8 | byte2
     * @param byte1-高8位
     * @param byte2-低8位
     * @return
     */
    public static short byteArrayToShort(byte byte1, byte byte2){
        int result = byte1 << 8 | byte2;
        return (short)result;
    }

    /**
     * short转int
     * @param s
     * @return
     */
    public static int shortToInt(short s){
        return s & 0xFFFFFFFF;
    }


    /**
     * long转int
     * @param l
     * big-endian 高位在前字节序
     * @return 2元素数组
     * result[0] -> 高位
     * result[1] -> 低位
     */
    public static int[] longToInt(long l){
        int lowBit = (int)(l & 0xFFFFFFFF);
        int upperBit = (int)((l >> 32) & 0xFFFFFFFF);

        int[] result = new int[2];
        result[0] = upperBit;
        result[1] = lowBit;

        return result;
    }

    /**
     * float转int
     * @param f
     * @return
     */
    public static int floatToInt(float f){
        return Float.floatToIntBits(f);
    }

    /**
     * double转int
     * big-endian 高位在前字节序
     * @param d
     * @return 2元素数组
     * result[0] -> 高位
     * result[1] -> 低位
     */
    public static int[] doubleToInt(double d){
        long l = Double.doubleToLongBits(d);
        return longToInt(l);
    }


    /**
     * int转byte数组
     * big-endian 高位在前字节序
     * @param i
     * @return
     * result[3] -> 低8位
     * result[2] -> 8-15位
     * result[1] -> 16-23位
     * result[0] -> 高8位
     */
    public static byte[] intToByte(int i){
        byte[] result = new byte[4];
        result[3] = (byte)(i & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[0] = (byte)((i >> 24) & 0xFF);
        return result;
    }

    /**
     * int 转 short数组
     * big-endian 高位在前字节序
     * @param i
     * @return
     * result[1] -> 低8位
     * result[0] -> 高8位
     *
     */
    public static short[] intToShort(int i){
        short[] result = new short[2];
        result[1] = (short)(i & 0xFFFF);
        result[0] = (short)((i >> 16) & 0xFFFF);
        return result;
    }


    /**
     * int转long
     * big-endian 高位在前字节序
     * long = int1 << 32 | int2
     * @param int1 - 高32位
     * @param int2 - 低32位
     * @return
     */
    public static long intToLong(int int1, int int2){
        int upperBit = int1;
        int lowBit = int2;

        return ((long)upperBit << 32) | ((long)lowBit & 0xFFFFFFFFL);
    }

    /**
     * int转float
     * @param i
     * @return
     */
    public static float intToFloat(int i){
        return Float.intBitsToFloat(i);
    }

    /**
     * int转double
     * big-endian 高位在前字节序
     * long = int1 << 32 | int2
     * @param int1 - 高32位
     * @param int2 - 低32位
     * @return
     */
    public static double intToDouble(int int1, int int2){
        long l = intToLong(int1, int2);
        return Double.longBitsToDouble(l);
    }
    
}
