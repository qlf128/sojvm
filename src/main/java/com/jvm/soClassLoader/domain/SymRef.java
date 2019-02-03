package com.jvm.soClassLoader.domain;

import java.io.IOException;

/**
 * @author luao
 * @date 2018-11-12 17:49
 */
public class SymRef{

    private ConstantPool cp;

    private String className;

    private SoClass soClass;

    /**
     * 如果类符号引用已经解析，直接返回类指针
     * 否则调用resolveClassRef()方法进行解析。
     * @return
     */
    public SoClass resolvedClass(){
        if (this.soClass==null){
            this.resolveClassRef();
        }
        return this.soClass;
    }

    /**
     * 解析类符号引用
     */
    public void resolveClassRef(){
        SoClass d = this.cp.soClass;
        SoClass c = d.getSoClassLoader().loadClass(this.className);
        if(!c.isAccessibleTo(d)){
            throw new IllegalAccessError();
        }
        this.soClass = c;
    }

    public ConstantPool getCp() {
        return this.cp;
    }

    public void setCp(ConstantPool cp) {
        this.cp = cp;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public SoClass getSoClass() {
        return soClass;
    }

    public void setSoClass(SoClass soClass) {
        this.soClass = soClass;
    }
}
