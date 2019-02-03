package com.jvm.classReader.model.attribute;

import com.jvm.classReader.ClassReader;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018-11-24 16:54
 */

public class LineNumberTableAttribute extends AttributeInfo {
    LineNumberTableEntry[] lineNumberTable;

    @Override
    void readInfo(ClassReader reader) {
        int lineNumberTableLength = reader.readUint16();
        this.lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
        for (int i = 0; i < lineNumberTableLength; i++) {
            lineNumberTable[i] = new LineNumberTableEntry(reader.readUint16(), reader.readUint16());
        }
    }

    /* 根据字节码中的行号,寻找其在源代码中的行号;一般情况下;多个字节码的行号可能会对应一个源文件中的一行
    0 - 15
    8 - 17
    14 - 21
    17 - 18
    18 - 20
    22 - 24
    可以确保的是字节码中的行号递增的,而对应的源码中的行号并不是
    */
    public int getLineNumber(int pc) {
        for (int i = lineNumberTable.length - 1; i >= 0; i--) {
            LineNumberTableEntry entry = lineNumberTable[i];
            if (pc >= entry.startPc) {
                return entry.lineNumber;
            }
        }
        return -1;
    }

    static class LineNumberTableEntry {
        int startPc;    //字节码行号
        int lineNumber; //Java源码行号，二者执行的关联

        public LineNumberTableEntry(int startPc, int lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }
    }
}
