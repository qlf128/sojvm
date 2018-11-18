package com.jvm.classReader;

import com.jvm.classReader.model.ConstantClass;
import com.jvm.classReader.model.ConstantMemberRef;
import com.jvm.classReader.model.ConstantNameAndType;
import com.jvm.classReader.model.ConstantUtf8;

import java.io.InputStream;

/**
 * @Description
 * @Auther mikicomo
 * @Date 2018/11/18 15:04
 */
public class ConstantPool {

    public int constant_pool_count;
    public ConstantInfo[] cpInfo;

    public ConstantPool(int count) {
        constant_pool_count = count;
        cpInfo = new ConstantInfo[constant_pool_count];
    }

    public void read(InputStream inputStream) throws Exception {
        for (int i = 1; i < constant_pool_count; i++) {
            short tag = ClassReader.readU1(inputStream);
            ConstantInfo constantInfo = ConstantInfo.getConstantInfo(tag);
            constantInfo.read(inputStream);
            cpInfo[i] = constantInfo;
            if (tag == ConstantInfo.CONSTANT_Double || tag == ConstantInfo.CONSTANT_Long) {
                i++;
            }
        }
    }

    public void printConstanPoolInfo(ConstantPool cp) {
        if (cp != null) {
            System.out.println("ConstantPool:" + cp.constant_pool_count);

            for (int i = 1; i < cp.constant_pool_count; i++) {
                ConstantInfo constantInfo = cp.cpInfo[i];
                if (constantInfo instanceof ConstantMemberRef) {
                    ConstantMemberRef memberRef = (ConstantMemberRef) constantInfo;
                    short tag = memberRef.tag;
                    switch (tag) {
                        case 9:
                            System.out.println("#" + i + " fieldref:" + ((ConstantUtf8) cp.cpInfo[memberRef.nameAndTypeIndex]).value);
                            continue;
                        case 10:
                            System.out.println("#" + i + " methodref:" + ((ConstantUtf8) cp.cpInfo[memberRef.nameAndTypeIndex]).value);
                            continue;
                        default:
                    }
                } else if (constantInfo instanceof ConstantNameAndType) {
                    ConstantNameAndType nameAndType_ = (ConstantNameAndType) constantInfo;
                    System.out.println("#" + i + " NameAndType        " + ((ConstantUtf8) cp.cpInfo[nameAndType_.nameIndex]).value);
                } else if (constantInfo instanceof ConstantClass) {
                    ConstantClass clazz = (ConstantClass) constantInfo;
                    System.out.println("#" + i + " class        " + ((ConstantUtf8) cp.cpInfo[clazz.nameIndex]).value);
                } else if (constantInfo instanceof ConstantUtf8) {
                    ConstantUtf8 utf_1 = (ConstantUtf8) constantInfo;
                    System.out.println("#" + i + " UTF-8        " + utf_1.value);
                }
            }
            System.out.println("\n");
        }
    }
}
