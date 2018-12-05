package com.jvm.instructions.references;

import com.jvm.instructions.Index16Instruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.soClassLoader.domain.*;

public class PUT_FIELD extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Method currentMethod = frame.getMethod();
        SoClass currentClass = currentMethod.getSoClass();
        ConstantPool cp = currentClass.getConstantPool();
        FieldRef fieldRef = (FieldRef) cp.getConstant(super.getIndex());
        Field field = fieldRef.resolvedField();
        if (field.isStatic()){
            throw new RuntimeException("IncompatibleClassChangeError");
        }
        if (field.isFinal()){
            //TODO 直接用 != 判断SoClass间的相等关系
            if (currentClass != field.getSoClass() || !currentMethod.getName().equals("<clinit>")){
                throw new RuntimeException("IllegalAccessError");
            }
        }
        String descriptor = field.getDescriptor();
        int slotId = field.getSoltId();
        OperandStack stack = frame.getOperandStack();
        SoObject ref = null;
        switch (descriptor.charAt(0)){
            case 'Z': case 'B': case 'C': case 'S': case 'I':
                int ival = stack.popInt();
                ref = stack.popObj();
                if (ref == null){
                    throw new RuntimeException("NullPointerException");
                }
                ref.getLocalVars().setInt(slotId, ival);
                break;
            case 'F':
                float fval = stack.popFloat();
                ref = stack.popObj();
                if (ref == null){
                    throw new RuntimeException("NullPointerException");
                }
                ref.getLocalVars().setFloat(slotId, fval);
                break;
            case 'J':
                long lval = stack.popLong();
                ref = stack.popObj();
                if (ref == null){
                    throw new RuntimeException("NullPointerException");
                }
                ref.getLocalVars().setLong(slotId, lval);
                break;
            case 'D':
                double dval = stack.popDouble();
                ref = stack.popObj();
                if (ref == null){
                    throw new RuntimeException("NullPointerException");
                }
                ref.getLocalVars().setDouble(slotId, dval);
                break;
            case 'L': case '[':
                SoObject refval = stack.popObj();
                ref = stack.popObj();
                if (ref == null){
                    throw new RuntimeException("NullPointerException");
                }
                ref.getLocalVars().setObj(slotId, refval);
                break;
                default:
                    break;
        }
    }
}
