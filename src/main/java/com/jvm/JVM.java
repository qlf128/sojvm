package com.jvm;

import com.jvm.instructions.base.ClassInitLogic;
import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.interpreter.Interpret;
import com.jvm.runTimeDateArea.model.*;
import com.jvm.search.ClassPath;
import com.jvm.soClassLoader.domain.Method;
import com.jvm.soClassLoader.domain.SoClass;
import com.jvm.soClassLoader.domain.SoClassLoader;
import org.apache.commons.lang3.StringUtils;

public class JVM {
    private Cmd cmd;
    private SoClassLoader classLoader;
    private SoThread mainThread;

    public JVM(Cmd cmd){
        ClassPath classPath = new ClassPath();
        ClassPath cp = classPath.parse(cmd.getXjreOption(),cmd.getCpOption());
        classLoader = new SoClassLoader(cp, cmd.isVerboseInstFlag());

        new JVM(cmd, classLoader, new SoThread());
    }

    public JVM(Cmd cmd, SoClassLoader classLoader, SoThread mainThread) {
        this.cmd = cmd;
        this.classLoader = classLoader;
        this.mainThread = mainThread;
    }

    public void start(){
        initVM();
        execMain();
    }

    public void initVM(){
        SoClass vmClass = classLoader.loadClass("sun/misc/VM");
        new ClassInitLogic().initClass(mainThread, vmClass);
        new Interpret().interpret(mainThread, cmd.isVerboseInstFlag());

    }

    public void execMain(){
        String className = StringUtils.replace(cmd.getClazz(),".","/",-1);
        SoClass mainClass = classLoader.loadClass(className);
        Method mainMethod = mainClass.getMainMethod();
        if(mainMethod == null){
            System.out.println("Main method not found in class "+cmd.getClazz());
            return;
        }

        SoObject argsArr = createArgsArray();
        Frame frame = mainThread.NewFrame(mainMethod);
        frame.getLocalVars().setObj(0,argsArr);
        mainThread.pushFrame(frame);
        new Interpret().interpret(mainThread, cmd.isVerboseInstFlag());
    }

    public SoObject createArgsArray(){
        SoClass stringClass = classLoader.loadClass("java/lang/String");
        int argsLen = cmd.getArgs().length;
        SoArrayObject argsArr = stringClass.arrayClass().newArray(argsLen);
        SoObject[] jArgs = argsArr.refs();

        for(int i=0; i<cmd.getArgs().length;i++){
            String arg = cmd.getArgs()[i];
            jArgs[i] = StringPool.jString(classLoader,arg);
        }
        return argsArr;
    }


    public Cmd getCmd() {
        return cmd;
    }

    public void setCmd(Cmd cmd) {
        this.cmd = cmd;
    }

    public SoClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(SoClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public SoThread getMainThread() {
        return mainThread;
    }

    public void setMainThread(SoThread mainThread) {
        this.mainThread = mainThread;
    }
}
