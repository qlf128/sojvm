package com.jvm.sun.misc;

import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.soClassLoader.domain.Method;
import com.jvm.soClassLoader.domain.SoClass;
import com.jvm.soClassLoader.domain.SoClassLoader;

public class VM {

/*
    public void init(){
        native.Register("sun/misc/VM","initialize","()V",initialize);
    }
*/

    public void initialize(Frame frame){
        SoClassLoader classLoader = frame.getMethod().getSoClass().getSoClassLoader();
        SoClass jlSysClass = classLoader.loadClass("java/lang/System");
        Method initSysClass = jlSysClass.getStaticMethod("initializeSystemClass","()V");
        MethodInvokeLogic.invokeMethod(frame,initSysClass);
    }

}
