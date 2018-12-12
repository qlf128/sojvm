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
        double d = DataTypeConvertUtil.intToDouble(iArray[0],iArray[1]);
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
        long l = DataTypeConvertUtil.intToLong(iArray[0],iArray[1]);
        return  l;
    }

    /**
     * 往栈顶放入Obj 与 pushRef等同
     */
    public  void pushObj(SoObject object){
        slots[size].setObj(object);
        size++;
    }
    public SoObject popObj(){
        size--;
        SoObject obj = slots[size].getObj();
        // 方便垃圾回收
        slots[size]=null;
        return obj;
    }

    public  void pushRef(SoObject object){
        slots[size].setObj(object);
        size++;
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

    /**
     * 获取距离操作数栈顶n个单元格的引用变量，n=0表示栈顶元素
     * @param n
     * @return
     */
    public SoObject getRefFromTop(int n){
        return this.slots[this.size-1-n].getObj();
    }


    /**
     *  清空
     */
    public  void clear(){
        this.size =0;
        for (int i = 0; i < this.slots.length; i++) {
            this.slots[i].setObj(null);
        }
    }

    /**
     *  往栈顶放入boolean
     */
    public void pushBoolean(boolean value){
        if(value){
            this.pushInt(1);
        }else {
            this.pushInt(0);
        }
    }
    public boolean popBoolean(){
        return popInt()==1;
    }

}
