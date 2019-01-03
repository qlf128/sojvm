package com.jvm.soClassLoader.domain;


import com.jvm.classReader.MemberInfo;
import com.jvm.classReader.model.attribute.CodeAttribute;
import com.jvm.classReader.model.attribute.LineNumberTableAttribute;
import com.jvm.heap.ExceptionHandler;
import com.jvm.heap.ExceptionTable;
import com.jvm.soClassLoader.constants.AccessFlagConstant;

import java.util.List;

/**
 * @author luao
 * @date 2018-11-12 16:07
 */
public class Method extends ClassMember {

    private int maxStack;

    private int maxLocals;

    private byte[] code;

    private int argSlotCount;

    private ExceptionTable exceptionTable;

    private LineNumberTableAttribute lineNumberTable;

    public static Method[] newMethods(SoClass soClass, MemberInfo[] cfMethods){
        if(cfMethods==null||cfMethods.length<=0){
            return null;
        }
        Method[] methods = new Method[cfMethods.length];
        for (int i = 0; i < cfMethods.length; i++) {
            Method method = new Method();
            method.setSoClass(soClass);
            method.copyClassMember(cfMethods[i]);
            method.copyAttributes(cfMethods[i]);
            method.calcArgSlotCount();
            methods[i] = method;
        }
        return methods;
    }

    public void copyAttributes(MemberInfo cfMethod){
        CodeAttribute codeAttr = cfMethod.getCodeAttribute();
        this.maxStack = codeAttr.getMaxStack();
        this.maxLocals = codeAttr.getMaxLocals();
        this.code = codeAttr.getCode();
        this.exceptionTable = new ExceptionTable(codeAttr.getExceptionTable(), this.getSoClass().getConstantPool());
    }

    public void calcArgSlotCount(){
        String descriptor = super.getDescriptor();
        MethodDescriptor methodDescriptor = MethodDescriptorParser.parsedMethodDescriptor(descriptor);
        List<String> paramTypes = methodDescriptor.getParamTypes();
        for (String paramType : paramTypes){
            this.argSlotCount++;
            if (paramType == null || paramType.isEmpty()){
                continue;
            }
            if (paramType.equals("J") || paramType.equals("D")){
                this.argSlotCount++;
            }
        }
        if (!super.isStatic()){
            this.argSlotCount++;
        }
    }

    public int findExceptionHandler(SoClass exClass, int pc){
        ExceptionHandler handler = exceptionTable.findExceptionHandler(exClass, pc);
        if(handler == null){
            return handler.getHandlerPc();
        }

        return -1;

    }

    public int getLineNumber(int pc){
        if (isNative()) {
            return -2;
        }

        if(getLineNumberTable() == null){
            return -1;
        }

        return getLineNumberTable().getLineNumber(pc);
    }

    public boolean isSynchronized(){
        return (super.getAccessFlags() & AccessFlagConstant.ACC_SYNCHRONIZED) != 0;
    }
    public boolean isBridge(){
        return (super.getAccessFlags() & AccessFlagConstant.ACC_BRIDGE) != 0;
    }
    public boolean isVarargs(){
        return (super.getAccessFlags() & AccessFlagConstant.ACC_VARARGS) != 0;
    }
    public boolean isNative(){
        return (super.getAccessFlags() & AccessFlagConstant.ACC_NATIVE) != 0;
    }
    public boolean isAbstract(){
        return (super.getAccessFlags() & AccessFlagConstant.ACC_ABSTRACT) != 0;
    }
    public boolean isStrict(){
        return (super.getAccessFlags() & AccessFlagConstant.ACC_STRICT) != 0;
    }


    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public int getArgSlotCount() {
        return argSlotCount;
    }

    public void setArgSlotCount(int argSlotCount) {
        this.argSlotCount = argSlotCount;
    }

    public LineNumberTableAttribute getLineNumberTable() {
        return lineNumberTable;
    }

    public void setLineNumberTable(LineNumberTableAttribute lineNumberTable) {
        this.lineNumberTable = lineNumberTable;
    }
}
