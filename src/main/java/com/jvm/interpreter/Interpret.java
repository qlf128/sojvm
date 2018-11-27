package com.jvm.interpreter;

import com.jvm.classReader.MemberInfo;
import com.jvm.classReader.model.attribute.CodeAttribute;
import com.jvm.instructions.BytecodeReader;
import com.jvm.instructions.Factory;
import com.jvm.instructions.Instructions;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.SoThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Interpret {
    private final Logger log = LoggerFactory.getLogger(Interpret.class);

    public void interpret(MemberInfo methodInfo){
        CodeAttribute codeAttr = methodInfo.getCodeAttribute();

        int maxLocals = codeAttr.getMaxLocals();
        int maxStack = codeAttr.getMaxStack();
        byte[] byteCode = codeAttr.getCode();

        //创建一个Thread实例，然后创建一个帧并把它推入Java虚拟机栈顶， 然后执行方法
        SoThread thread = new SoThread();
        Frame frame = thread.NewFrame(maxLocals, maxStack);
        thread.pushFrame(frame);

        try{
            loop(thread, byteCode, new BytecodeReader());
        }catch(Exception e){
            log.error("LocalVars:{}",frame.getLocalVars().toString());
            log.error("OperandStack:{}",frame.getOperandStack().toString());
            panic(e);
        }
    }

    private void loop(SoThread thread, byte[] bytecode, BytecodeReader reader){
        Frame frame = thread.popFrame();
        for(;;){
            int pc = frame.getNextPC();
            thread.setPcCount(pc);
            //decode
            reader.reset(bytecode,pc);
            int opcode = reader.readUint8();
            Instructions inst = Factory.newInstruction(opcode);
            inst.fetchOperands(reader);
            frame.setNextPC(reader.getPc());
            //execute
            log.info("pc:{} inst:name-{},address-{}",pc, inst.getClass().toString(),inst);
            inst.execute(frame);
        }
    }
}
