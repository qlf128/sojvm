package com.jvm.runTimeDateArea.model;

import com.jvm.soClassLoader.domain.Field;
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

        Object object = this.getArrayData();
        if(object instanceof byte[]){
            return bytes().length;
        }else if(object instanceof short[]){
            return shorts().length;
        }else if(object instanceof int[]){
            return ints().length;
        }else if(object instanceof long[]){
            return longs().length;
        }else if(object instanceof char[]){
            return chars().length;
        }else if(object instanceof float[]){
            return floats().length;
        }else if(object instanceof  double[]){
            return doubles().length;
        }else if(object instanceof SoObject[]){
            return refs().length;
        }
        System.out.println("No Array");
        return  0;
    }

    public SoArrayObject(SoClass soClass,Object data) {
        super(soClass,data);

    }
}
