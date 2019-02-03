package com.jvm.runTimeDateArea.model;

import com.jvm.soClassLoader.domain.SoClass;
import com.jvm.soClassLoader.domain.SoClassLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangfa
 * @Date: 2018/12/12 15:54
 * @Description:
 */
public class StringPool {

    private final static Map<String,SoObject> internedStrings = new HashMap<String,SoObject>();

    //存String 利用 map 的key为String ，SoObject 的data 为String进行存储
    public static SoObject jString(SoClassLoader classLoader,String goStr){

        if(goStr.equals(internedStrings.get(goStr))){
            return internedStrings.get(goStr);
        }

        SoObject strObj = new SoObject(classLoader.loadClass("[C"),goStr,null);

        SoObject soObject = classLoader.loadClass("java/lang/String").newObject();
        soObject.setRefVar("value","[C",strObj);
        internedStrings.put(goStr,soObject);
        return  soObject;
    }

    // 取String
    public static String goString(SoObject soObject){
        SoObject value = soObject.getRefVar("value", "[C");
        return (String)value.getStrData();

    }


    public static  SoObject internString(SoObject jstr){

        String goStr = goString(jstr);

        for(Map.Entry<String,SoObject> entrty : internedStrings.entrySet() ){
            String  str = entrty.getKey();
            if(goStr == str){
                return entrty.getValue();
            }
        }
        internedStrings.put(goStr,jstr);
        return jstr;

    }
}
