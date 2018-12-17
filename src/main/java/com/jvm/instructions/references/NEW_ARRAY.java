package com.jvm.instructions.references;

import com.jvm.instructions.BytecodeReader;
import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoArrayObject;
import com.jvm.runTimeDateArea.model.Stack;
import com.jvm.soClassLoader.domain.SoArrayClass;
import com.jvm.soClassLoader.domain.SoClass;
import com.jvm.soClassLoader.domain.SoClassLoader;

/**
 * @Author: wangfa
 * @Date: 2018/12/7 11:08
 * @Description:   newarray指令用来创建基本类型数组，包括boolean[]、byte[]、char[]、short[]、int[]、long[]、float[]和double[]8种。
 */
public class NEW_ARRAY extends NoOperandsInstruction {

    private int atype;


    class Constants{
        private static final  int AT_BOOLEAN = 4;
        private static final  int AT_CHAR = 5;
        private static final  int AT_FLOAT = 6;
        private static final  int AT_DOUBLE = 7;
        private static final  int AT_BYTE = 8;
        private static final  int AT_SHORT = 9;
        private static final  int AT_INT = 10;
        private static final  int AT_LONG = 11;
    }

    @Override
    public void fetchOperands(BytecodeReader bytecodeReader){
         this.atype =bytecodeReader.readUint8();
    }

    @Override
    public void execute(Frame frame) {

        OperandStack  stack =frame.getOperandStack();
        int count = stack.popInt();
        if(count<0){
            System.err.println("创建数组失败");
        }
        SoClassLoader soClassLoader = frame.getMethod().getSoClass().getSoClassLoader();
        SoArrayClass soArrayClass = getPrimitiveArrayClass(soClassLoader,atype);
        SoArrayObject object = soArrayClass.newArray(count);
        stack.pushObj(object);
    }

    private SoArrayClass getPrimitiveArrayClass(SoClassLoader loader,int atypes){
        switch (atypes){
            case Constants.AT_BOOLEAN:
                return (SoArrayClass) loader.loadClass("[Z");
            case Constants.AT_BYTE:
                return (SoArrayClass) loader.loadClass("[B");
            case Constants.AT_CHAR:
                return (SoArrayClass) loader.loadClass("[C");
            case Constants.AT_SHORT:
                return (SoArrayClass) loader.loadClass("[S");
            case Constants.AT_INT:
                return (SoArrayClass) loader.loadClass("[I");
            case Constants.AT_LONG:
                return (SoArrayClass) loader.loadClass("[J");
            case Constants.AT_FLOAT:
                return (SoArrayClass) loader.loadClass("[F");
            case Constants.AT_DOUBLE:
                return (SoArrayClass) loader.loadClass("D");
                default:
                    System.err.println("atype is invalid");
                    System.exit(-1);
                    return null;

        }

    }
}
