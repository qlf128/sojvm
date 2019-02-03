package com.jvm.soClassLoader.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangfa
 * @Date: 2018/12/7 13:58
 * @Description: anewarray指令使用类
 */
public  class ClassNameHelper {

    private static final Map<String,String> primitiveTypes = new HashMap<>();

    static {
        primitiveTypes.put("void","V");
        primitiveTypes.put("boolean","Z");
        primitiveTypes.put( "byte","B");
        primitiveTypes.put("short","S");
        primitiveTypes.put("int","I");
        primitiveTypes.put("long","J");
        primitiveTypes.put("char","C");
        primitiveTypes.put("float","F");
        primitiveTypes.put("double","D");
    }



    // 一维数组
    public static String getArrayClassName(String className){
        return "["+ toDescriptor(className);
    }
    // 二维数组
    public static String getComponentClassName(String className){

        if(className.startsWith("[")){
            String componentTypeDescriptor = className.substring(1);
            return toClassName(componentTypeDescriptor);
        }
        System.err.println("Not array:"+className);
        System.exit(-1);
        return null;
    }

    private static String toClassName(String descriptor) {
        if(descriptor.startsWith("[")){
            return descriptor;
        }
        if(descriptor.startsWith("L")){
            return descriptor.substring(1,descriptor.length()-1);
        }

        for(Map.Entry<String,String> s : primitiveTypes.entrySet()){
            if(s.getValue().equals(descriptor)){
                return s.getValue();
            }
        }
        System.err.println("Invalid descriptor:" + descriptor);
        System.exit(-1);
        return null;
    }


    private static String toDescriptor(String className) {

        // 如果是数组类名，描述符就是类名，直接返回即可。如果是基本类型名，返回对应的类型描述符，否则肯定是普通的类名，前面加上方括号(代码为L，在多维数组中有用)，结尾加上分号即可得到类型描述符。
        if(className.startsWith("[")){
            // 是array
            return className;
        }

        if(primitiveTypes.get(className)!=null){
            return primitiveTypes.get(className);
        }
        return "L"+className+";";
    }


}
