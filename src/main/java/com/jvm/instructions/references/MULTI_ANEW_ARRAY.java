package com.jvm.instructions.references;

import com.jvm.instructions.base.BytecodeReader;
import com.jvm.instructions.base.instruction.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoArrayObject;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.soClassLoader.domain.ClassRef;
import com.jvm.soClassLoader.domain.ConstantPool;
import com.jvm.soClassLoader.domain.SoArrayClass;

import java.util.Arrays;

/**
 * @Author: wangfa
 * @Date: 2018/12/7 16:08
 * @Description: multianewarray指令创建多维数组
 */
public class MULTI_ANEW_ARRAY extends NoOperandsInstruction {

    private int index;
    private int dimensions;



    @Override
    public void fetchOperands(BytecodeReader byteCodeReader) {
        index = byteCodeReader.readUint16();
        dimensions =byteCodeReader.readUint8();
    }

    @Override
    public void execute(Frame frame) {

        ConstantPool cp = frame.getMethod().getSoClass().getConstantPool();
        ClassRef classRef = (ClassRef) cp.getConstant(index);
        SoArrayClass arrayClass = (SoArrayClass) classRef.resolvedClass();

        OperandStack stack = frame.getOperandStack();
        int[] counts  =popAndCheckCounts(stack,dimensions);
        SoArrayObject soObject = newMultiDimensionalArray(counts, arrayClass);
    }


    private int[] popAndCheckCounts(OperandStack stack, int dimensions) {
        int[] counts = new int[dimensions];
        for (int i = dimensions-1; i <=0 ; i--) {
            counts[i] = stack.popInt();
            if(counts[i]<0){
                System.err.println("java.lang.NegativeArraySizeException");
            }
        }
        return counts;
    }

    private SoArrayObject newMultiDimensionalArray(int[] counts, SoArrayClass arrayClass) {
        int count = counts[0];
        SoArrayObject object = arrayClass.newArray(count);

        if(counts.length>1) {
            SoObject[] refs = object.refs();
            for(int i=0;i<refs.length;i++){
                refs[i] = newMultiDimensionalArray(Arrays.copyOfRange(counts,1,counts.length),arrayClass.componentClass());
            }
        }
        return object;
    }



}
