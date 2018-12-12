package com.jvm.runTimeDateArea.model;

import com.jvm.soClassLoader.domain.ClassHierarchy;
import com.jvm.soClassLoader.domain.Field;
import com.jvm.soClassLoader.domain.SoClass;

import java.util.logging.SocketHandler;

/**
 * @author luao
 * @date 2018-11-13 17:28
 */
public class SoObject {
    private SoClass soClass;


    private Object data;

    private Object extra;


    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

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
    public LocalVars getData() {
        return (LocalVars)data;
    }

    /**
     *  得到数组对象数据
     */
    public Object getArrayData(){
        return  data;
    }

    public Object getStrData() {
        return data;
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

    public SoObject(SoClass soClass, Object data, Object extra) {
        this.soClass = soClass;
        this.data = data;
        this.extra = extra;
    }

    /**
     *  用于普通对象数据
     */
    public LocalVars fields(){
        return this.getData();
    }

    public static SoObject createObject(SoClass soClass){
        LocalVars localVars = new LocalVars(soClass.getInstanceSlotCount());
        return new SoObject(soClass, localVars);
    }

    public boolean isInstanceOf(SoClass soClass){
        return ClassHierarchy.isAssignableFrom(soClass, this.soClass);
    }


    public void setRefVar(String name,String descriptor, SoObject soObject){
        Field field = this.soClass.getField(name, descriptor, false);
        LocalVars  slots = this.getData();
        slots.setObj(field.getSoltId(),soObject);
    }
    public SoObject getRefVar(String name,String descriptor){
        Field field = this.soClass.getField(name,descriptor,false);
        LocalVars  slots = this.getData();
        return  slots.getObj(field.getSoltId());
    }




   /*// 数组 更改Object 使其可以兼容任何类型的值
   private LocalVars localVars;
    public SoObject(SoClass soClass, LocalVars localVars) {
        this.soClass = soClass;
        this.localVars = localVars;
    }
    public LocalVars getLocalVars() {
        return localVars;
    }

    public void setLocalVars(LocalVars localVars) {
        this.localVars = localVars;
    }*/


}
