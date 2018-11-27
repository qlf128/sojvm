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

    public static int byteArrayToInt(byte[] bArray){
        int result;
        result = bArray[0]
                | bArray[1]<<8
                | bArray[2]<<16
                | bArray[3]<<24;
        return result;
    }

    public static short byteArrayToShort(byte[] bArray){
        int result;
        result = bArray[0]
                | bArray[1]<<8;
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
     * @return 2元素数组
     * result[0] -> 低位
     * result[1] -> 高位
     */
    public static int[] longToInt(long l){
        int lowBit = (int)(l & 0xFFFFFFFF);
        int upperBit = (int)((l >> 32) & 0xFFFFFFFF);

        int[] result = new int[2];
        result[0] = lowBit;
        result[1] = upperBit;

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
     * @param d
     * @return 2元素数组
     * result[0] -> 低位
     * result[1] -> 高位
     */
    public static int[] doubleToInt(double d){
        long l = Double.doubleToLongBits(d);
        return longToInt(l);
    }


    /**
     * int转byte数组
     * @param i
     * @return
     * result[0] -> 低8位
     * result[1] -> 8-15位
     * result[2] -> 16-23位
     * result[3] -> 高8位
     */
    public static byte[] intToByte(int i){
        byte[] result = new byte[4];
        result[0] = (byte)(i & 0xFF);
        result[1] = (byte)((i >> 8) & 0xFF);
        result[2] = (byte)((i >> 16) & 0xFF);
        result[3] = (byte)((i >> 24) & 0xFF);
        return result;
    }

    /**
     * int 转 short数组
     * @param i
     * @return
     * result[0] -> 低8位
     * result[1] -> 高8位
     *
     */
    public static short[] intToShort(int i){
        short[] result = new short[2];
        result[0] = (short)(i & 0xFFFF);
        result[1] = (short)((i >> 16) & 0xFFFF);
        return result;
    }

    /**
     * int数组转long
     * @param iArray
     * @return
     * iArray[0] -> 高位
     * iArray[1] -> 低位
     */
    public static long intToLong(int[] iArray){
        int upperBit = iArray[0];
        int lowBit = iArray[1];

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
     * @param iArray
     * @return
     * Array[0] -> 高位
     * iArray[1] -> 低位
     */
    public static double intToDouble(int[] iArray){
        long l = intToLong(iArray);
        return Double.longBitsToDouble(l);
    }
    
}
