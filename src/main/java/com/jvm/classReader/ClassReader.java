package com.jvm.classReader;

import com.jvm.util.ByteUtils;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 19:01
 */

public class ClassReader {

    private byte[] data;
    private int index = 0;

    public ClassReader(byte[] data) {
        this.data = data;
    }

    // u1
    public byte readUint8() {
        byte res = data[index++];
        return res;
    }


    // u2 这里是读取一个无符号的16位整,用int来代替吧;
    public int readUint16() {
        byte[] res = new byte[2];
        res[0] = data[index++];
        res[1] = data[index++];
        return ByteUtils.bytesToU16(res);
    }

    // u4
    public byte[] readUint32() {
        byte[] res = new byte[4];
        res[0] = data[index++];
        res[1] = data[index++];
        res[2] = data[index++];
        res[3] = data[index++];
//        return ByteUtils.bytesToU32(res);
        return res;
    }

    public byte[] readUint64() {
        byte[] res = new byte[8];
        res[0] = data[index++];
        res[1] = data[index++];
        res[2] = data[index++];
        res[3] = data[index++];
        res[4] = data[index++];
        res[5] = data[index++];
        res[6] = data[index++];
        res[7] = data[index++];
        return res;
    }

    /**
     * 读取连续的16bit长的数组,首先读出16bit,用来表示接下来要去读的多少个16bit
     *
     * @return
     */
    public int[] readUint16s() {
        int n = readUint16();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = readUint16();
        }
        return data;
    }

    public byte[] readBytes(int n) {
        byte[] res = new byte[n];
        for (int i = 0; i < n; i++) {
            res[i] = data[index++];
        }
        return res;
    }

}
