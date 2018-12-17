package com.jvm.soClassLoader.domain;

import java.util.List;

public class MethodDescriptor {
    private List<String> paramTypes;
    private String returnType;

    public void addParameterType(String t){
        this.paramTypes.add(t);
    }

    public List<String> getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(List<String> paramTypes) {
        this.paramTypes = paramTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
