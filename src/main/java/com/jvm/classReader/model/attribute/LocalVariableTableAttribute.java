package com.jvm.classReader.model.attribute;

import com.jvm.classReader.ClassReader;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class LocalVariableTableAttribute extends AttributeInfo {
    LocalVariableTableEntry[] localVariableTable;

    @Override
    void readInfo(ClassReader reader) {
        int localVariableTableLength = reader.readUint16();
        this.localVariableTable = new LocalVariableTableEntry[localVariableTableLength];
        for (int i = 0; i < localVariableTableLength; i++) {
            localVariableTable[i] = new LocalVariableTableEntry(
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16()
            );
        }
    }

    static class LocalVariableTableEntry {
        int startPc;    //代表该局部变量的生命周期开始的字节码偏移量
        int length;     //代表该局部变量的作用范围所覆盖的长度
        int nameIndex;    //指向常量池中个CONSTANT_Utf8_info型常量的索引，代表局部变量名称
        int descriptorIndex;    //指向常量池中个CONSTANT_Utf8_info型常量的索引，变量描述符
        int index;      //该局部变量在栈帧局部变量包中slot的位置

        public LocalVariableTableEntry(int startPc, int length, int nameIndex, int descriptorIndex, int index) {
            this.startPc = startPc;
            this.length = length;
            this.nameIndex = nameIndex;
            this.descriptorIndex = descriptorIndex;
            this.index = index;
        }
    }
}
