package com.jvm.natives.java.lang;

import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.LocalVars;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.soClassLoader.domain.SoClass;

public class System_J {

    // private static native void setOut0(PrintStream out);
    // (Ljava/io/PrintStream;)V
    public void setOut0(Frame frame){
        LocalVars vars = frame.getLocalVars();
        SoObject out = vars.getObj(0);

        SoClass sysClass = frame.getMethod().getSoClass();
        sysClass.setRefVar("out","Ljava/io/PrintStream;",out);
    }

    // private static native void setIn0(InputStream in);
    // (Ljava/io/InputStream;)V
    public void setIn0(Frame frame){
        LocalVars vars = frame.getLocalVars();
        SoObject in = vars.getObj(0);

        SoClass sysClass = frame.getMethod().getSoClass();
        sysClass.setRefVar("in","Ljava/io/InputStream;",in);

    }

}
