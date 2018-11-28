package com.jvm.runTimeDateArea.model;

import com.jvm.soClassLoader.domain.SoClass;

import java.util.logging.SocketHandler;

/**
 * @author luao
 * @date 2018-11-13 17:28
 */
public class SoObject {
    private SoClass soClass;

    private LocalVars localVars;

    public SoClass getSoClass() {
        return soClass;
    }

    public void setSoClass(SoClass soClass) {
        this.soClass = soClass;
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public void setLocalVars(LocalVars localVars) {
        this.localVars = localVars;
    }
}
