package com.jvm.heap;

import com.jvm.classReader.model.attribute.CodeAttribute;
import com.jvm.soClassLoader.domain.ClassRef;
import com.jvm.soClassLoader.domain.ConstantPool;
import com.jvm.soClassLoader.domain.SoClass;

public class ExceptionTable {
    private ExceptionHandler[] exceptionHandlers;

    public ExceptionTable newExceptionTable(CodeAttribute.ExceptionTableEntry[] entries, ConstantPool cp){
        ExceptionHandler[] exceptionHandlers = new ExceptionHandler[entries.length];

        for(int i=0; i<entries.length; i++){
            CodeAttribute.ExceptionTableEntry entry = entries[i];
            exceptionHandlers[i] = new ExceptionHandler(entry.getStartPc(), entry.getEndPc(), entry.getHandlerPc(), getCatchType(entry.getCatchType(), cp));
        }

        return new ExceptionTable(exceptionHandlers);

    }

    public ClassRef getCatchType(int index, ConstantPool cp){
        if(index == 0){
            return null;
        }
        return (ClassRef)cp.getConstant(index);
    }

    public ExceptionHandler findExceptionHandler(SoClass exClass, int pc){
        for(int i=0; i<exceptionHandlers.length; i++){
            ExceptionHandler handler = exceptionHandlers[i];
            if(pc >= handler.getStartPc() && pc < handler.getEndPc()){
                if(handler.getCatchType() == null){
                    return handler;
                }

                SoClass catchClass = handler.getCatchType().resolvedClass();
                if(exClass == catchClass || catchClass.isSuperClassOf(exClass)){
                    return handler;
                }
            }
        }
        return null;
    }

    public ExceptionTable(ExceptionHandler[] exceptionHandlers) {
        this.exceptionHandlers = exceptionHandlers;
    }

    public ExceptionHandler[] getExceptionHandlers() {
        return exceptionHandlers;
    }

    public void setExceptionHandlers(ExceptionHandler[] exceptionHandlers) {
        this.exceptionHandlers = exceptionHandlers;
    }
}
