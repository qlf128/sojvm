package com.jvm.instructions.base;

import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoThread;
import com.jvm.soClassLoader.domain.Method;
import com.jvm.soClassLoader.domain.SoClass;

/**
 * 递归初始化当前类及其父类
 */
public class ClassInitLogic {

    public static void initClass(SoThread thread, SoClass soClass){
        soClass.startInit();
        sheduleClinit(thread,soClass);
        initSuperClass(thread,soClass);
    }

    private static void sheduleClinit(SoThread thread, SoClass soClass){
        Method clinit = soClass.getClinitMethod();
        if (clinit != null){
            Frame newFrame = thread.NewFrame(clinit);
            thread.pushFrame(newFrame);
        }
    }

    private static void initSuperClass(SoThread soThread, SoClass soClass){
        if (! soClass.isInterface()){
            SoClass superClass = soClass.getSuperClass();
            if (superClass != null && !superClass.isInitStarted()){
                initClass(soThread,superClass);
            }
        }
    }
}
