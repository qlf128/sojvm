package com.jvm.natives.lang.throwable;

import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.runTimeDateArea.model.SoThread;
import com.jvm.soClassLoader.domain.Method;
import com.jvm.soClassLoader.domain.SoClass;

public class StackTraceElement {
    private String fileName;
    private String className;
    private String methodName;
    private int lineNumber;

    public StackTraceElement(String fileName, String className, String methodName, int lineNumber) {
        this.fileName = fileName;
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
    }

    public void fillInStackTrace(Frame frame){
        SoObject thisObj = frame.getLocalVars().getThis();
        frame.getOperandStack().pushRef(thisObj);


    }

    public StackTraceElement[] createStackTraceElements(SoObject tObj, SoThread thread){
        int skip = distanceToObject(tObj.getSoClass())  + 2;
        Frame[] frames = new Frame[thread.getFrames().length];
        for(int i=skip; i<thread.getFrames().length; i++){
            frames[i] = thread.getFrames()[i];
        }
        StackTraceElement[] stes = new StackTraceElement[frames.length];
        for(int j=0; j<frames.length; j++){
            stes[j] = createStackTraceElement(frames[j]);
        }

        return stes;
    }

    public int distanceToObject(SoClass clazz){
        int distance = 0;
        for(SoClass c = clazz.getSuperClass(); c != null; c = c.getSuperClass()){
            distance++;
        }
        return distance;
    }

    public StackTraceElement createStackTraceElement(Frame frame){
        Method method = frame.getMethod();
        SoClass clazz = method.getSoClass();

        return new StackTraceElement(clazz.getSourceFile(),
                clazz.javaName(),
                method.getName(),
                method.getLineNumber(frame.getNextPC() - 1));
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
