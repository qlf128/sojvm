package com.jvm.runTimeDateArea.model;

import com.jvm.soClassLoader.domain.SoClass;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangfa
 * @Date: 2018/11/28 16:28
 * @Description: 针对数组的Object 处理方法
 */
public class SoArrayObject extends SoObject {


    public byte[] bytes(){
        return  (byte[])getArrayData();
    }

    public short[] shorts(){
        return (short[])getArrayData();
    }
    public int[] ints(){
        return (int[])getArrayData();
    }
    public long[] longs(){
        return (long[])getArrayData();
    }
    public char[] chars(){
        return (char[])getArrayData();
    }
    public float[] floats(){
        return (float[])getArrayData();
    }
    public double[] doubles(){
        return (double[])getArrayData();
    }
    public SoObject[] refs(){
        return (SoObject[])getArrayData();
    }

    //TODO 得到数组长度
    public int arrayLength(){

        if(true){
            return bytes().length;

        }else if(true){
            return shorts().length;

        }else if(true){

            return ints().length;
        }else if(true){
            return longs().length;
        }else if(true){
            return chars().length;
        }else if(true){
            return floats().length;
        }else if(true){
            return doubles().length;
        }else if(true){
            return refs().length;
        }
        return  0;
    }

    public SoArrayObject(SoClass soClass,Object data) {
        super(soClass,data);

    }
}
