package com.jvm.runTimeDateArea.model;

import com.jvm.soClassLoader.domain.SoClass;

import java.util.logging.SocketHandler;

/**
 * @author luao
 * @date 2018-11-13 17:28
 */
public class SoObject {
    private SoClass soClass;

   // private LocalVars localVars;
    private Object data;

    public SoClass getSoClass() {
        return soClass;
    }

    public void setSoClass(SoClass soClass) {
        this.soClass = soClass;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     *  得到普通对象数据
     */
    public Slot[] getData() {
        return (Slot[])data;
    }

    /**
     *  得到数组对象数据
     */
    public Object getArrayData(){
        return  data;
    }

    public SoObject() {
    }


    /**
     * 用来创建普通对象
     */
    public SoObject(SoClass soClass) {
        this.soClass =soClass;
        data = new Slot[soClass.getInstanceSlotCount()];
    }

    public SoObject(SoClass soClass, Object data) {
        this.soClass = soClass;
        this.data = data;
    }
}
