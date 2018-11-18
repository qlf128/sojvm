package com.jvm.classReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/13 00:11
 */
public class ClassReader {

    private static Logger logger = LoggerFactory.getLogger(ClassReader.class);

    /**
     * 功能描述: u1 解析
     *
     * @auther mikicomo
     * @date 2018/11/13
     */
    public static short readU1(InputStream inputStream) {
        byte[] bytes = new byte[1];
        try {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        short value = (short) (bytes[0] & 0xFF);
        return value;
    }

    /**
     * 功能描述: u2 解析
     *
     * @auther mikicomo
     * @date 2018/11/13
     */
    public static int readU2(InputStream inputStream) {
        //bytes作为缓冲数组存储两个字节
        //class文件中字符以U-16编码
        byte[] bytes = new byte[2];
        try {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将缓冲数组中的两个字节解析成字符。
        int num = 0;
        for (byte aByte : bytes) {

            // num=num*2^8
            num <<= 8;

            num |= (aByte & 0xff);
        }
        return num;
    }

    /**
     * 功能描述: u4 解析
     *
     * @auther mikicomo
     * @date 2018/11/13
     */
    public static long readU4(InputStream inputStream) {
        byte[] bytes = new byte[4];
        try {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long num = 0;
        for (byte aByte : bytes) {
            num <<= 8;
            num |= (aByte & 0xff);
        }
        return num;
    }

}
