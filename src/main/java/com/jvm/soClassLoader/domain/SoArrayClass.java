package com.jvm.soClassLoader.domain;

import com.jvm.runTimeDateArea.model.SoArrayObject;
import com.jvm.runTimeDateArea.model.SoObject;

/**
 * @Author: wangfa
 * @Date: 2018/11/29 10:14
 * @Description: 数组类
 */
public class SoArrayClass extends SoClass {

    public boolean isArray() {
        return getName().startsWith("[");
    }


    /**
     * 创建数组对象
     */
    public SoArrayObject newArray(int count) {

        if (!isArray()) {
            System.err.println(this.getName());
        }

        String str = getName().substring(0, 2);
        // z boolean；B byte；C char；D double；F float；I int；J long；S short；
        switch (str) {
            case "[Z":
               boolean[] z = new boolean[count];
                return new SoArrayObject(this,z);
            case "[B":
                byte[] b = new byte[count];
                return new SoArrayObject(this,b);
            case "[C":
                char[] c = new char[count];
                return new SoArrayObject(this,c);
            case "[S":
                short[] s = new short[count];
                return new SoArrayObject(this,s);
            case "[I":
                int[] i = new int[count];
                return new SoArrayObject(this,i);
            case "[J":
                long[] l = new long[count];
                return new SoArrayObject(this,l);
            case "[F":
                float[] f = new float[count];
                return new SoArrayObject(this,f);
            case "[D":
                double[] d = new double[count];
                return new SoArrayObject(this,d);
            default:
                SoObject[] o = new SoObject[count];
                return new SoArrayObject(this,o);
        }

    }

    public SoArrayClass componentClass(){

        String className = ClassNameHelper.getComponentClassName(this.getName());
       return (SoArrayClass) this.getSoClassLoader().loadClass(className);

    }

}
