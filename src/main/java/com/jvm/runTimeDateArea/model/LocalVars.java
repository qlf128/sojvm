package com.jvm.runTimeDateArea.model;

import com.jvm.util.DataTypeConvertUtil;

/**
 * @Author: wangfa
 * @Date: 2018/11/11 11:28
 * @Description:  局部变量表 类型
 */
public class LocalVars {

    private Slot[] slots;

    public Slot[] getSlots() {
        return slots;
    }

    public void setSlots(Slot[] slots) {
        this.slots = slots;
    }

    public LocalVars(int maxLocals) {
        if(maxLocals>0) {
            slots = new Slot[maxLocals];
        }else {
            slots =null;
        }
    }

    /**
     *  存取Int 类型   byte short char 能转化为int
     */
    public void setInt(int index ,int value){
        slots[index].setNum(value);
    }
    public int getInt(int index){
        return slots[index].getNum();
    }

    /**
     *  存取float 类型
     */
    public void setFloat(int index ,float value){
        int floatToInt = DataTypeConvertUtil.floatToInt(value);
        slots[index].setNum(floatToInt);
    }
    public float getFloat(int index){
        int num = slots[index].getNum();
        DataTypeConvertUtil.intToFloat(num);
        return num;
    }

    /**
     *  存取double 类型
     */
    public void setDouble(int index ,double value){
        int[] doubleToInt = DataTypeConvertUtil.doubleToInt(value);
        slots[index].setNum(doubleToInt[0]);
        slots[index+1].setNum(doubleToInt[1]);
    }
    public double getDouble(int index){
        int[] iArray = new int[2];
        iArray[0]= slots[index].getNum();
        iArray[1] = slots[index+1].getNum();
        return  DataTypeConvertUtil.intToDouble(iArray[0],iArray[1]);
    }

    /**
     *  存取Long 类型
     */
    public void setLong(int index ,long value){
        int[] longToInt = DataTypeConvertUtil.longToInt(value);
        slots[index].setNum(longToInt[0]);
        slots[index+1].setNum(longToInt[1]);
    }
    public long getLong(int index){
        int[] iArray = new int[2];
        iArray[0]= slots[index].getNum();
        iArray[1] = slots[index+1].getNum();
        return  DataTypeConvertUtil.intToLong(iArray[0],iArray[1]);
    }

    /**
     *  存取Object 类型
     */
    public void setObj(int index ,SoObject value){
        slots[index].setObj(value);
    }
    public SoObject getObj(int index){
        return  slots[index].getObj();
    }

    //存取Slot
    public void setSlot(int index, Slot slot){
        this.slots[index] = slot;
    }
}
