package com.jvm.runTimeDateArea.model;

import com.jvm.util.DataTypeConvertUtil;

/**
 * @Author: wangfa
 * @Date: 2018/11/11 11:31
 * @Description:  操作数栈(后进先出)
 */
public class OperandStack {


    /**
     * 记录栈顶位置
     */
     private  int  size;

    private  Slot[] slots;


    public OperandStack(int maxLocals) {
        if(maxLocals>0) {
            slots = new Slot[maxLocals];
        }else {
            slots =null;
        }
        size=0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void setSlots(Slot[] slots) {
        this.slots = slots;
    }


    /**
     *  往栈顶放入int
     */
    public void pushInt(int value){
        slots[size].setNum(value);
        size++;
    }
    public int popInt(){
        size--;
        return slots[size].getNum();
    }

    /**
     *  往栈顶放入float
     */
    public void pushFloat(float value){
        int floatToInt = DataTypeConvertUtil.floatToInt(value);
        slots[size].setNum(floatToInt);
        size++;
    }
    public float popFloat(){
        size--;
        int num = slots[size].getNum();
        float f = DataTypeConvertUtil.intToFloat(num);
        return  f;
    }

    /**
     * 往 栈顶放入double
     */
    public void  pushDouble(double value){
        int[] longToInt = DataTypeConvertUtil.doubleToInt(value);
        slots[size].setNum(longToInt[0]);
        slots[size++].setNum(longToInt[1]);
        size+=2;
    }
    public double popDouble(){
        size-=2;
        int[] iArray = new int[2];
        iArray[0] = slots[size].getNum();
        iArray[1] = slots[size++].getNum();
        double d = DataTypeConvertUtil.intToDouble(iArray);
        return d;
    }

    /**
     *  往栈顶放入long
     */
    public void  pushLong(long value){
        int[] longToInt = DataTypeConvertUtil.longToInt(value);
        slots[size].setNum(longToInt[0]);
        slots[size++].setNum(longToInt[1]);
        size+=2;
    }
    public long popLong(){
        size-=2;
        int[] iArray = new int[2];
        iArray[0] = slots[size].getNum();
        iArray[1] = slots[size++].getNum();
        long l = DataTypeConvertUtil.intToLong(iArray);
        return  l;
    }

    /**
     * 往栈顶放入Obj
     */
    public  void pushObj(Object object){
        slots[size].setObj(object);
        size++;
    }
    public Object popObj(){
        size--;
        Object obj = slots[size].getObj();
        // 方便垃圾回收
        slots[size]=null;
        return obj;
    }

    /**
     *  栈指令 入栈
     */
    public void pushSlot(Slot slot){
        slots[size] =slot;
        size++;
    }
    /**
     *  栈指令 出栈
     */
    public Slot popSlot(){
        size--;
        return slots[size];
    }

}
