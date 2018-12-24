package com.jvm.instructions.references;

import com.jvm.instructions.Factory;
import com.jvm.instructions.NoOperandsInstruction;
import com.jvm.runTimeDateArea.model.Frame;
import com.jvm.runTimeDateArea.model.OperandStack;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.runTimeDateArea.model.SoThread;
import com.jvm.util.NativeMethodUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Throw exception or error
 */
public class ATHROW extends NoOperandsInstruction{
    private final Logger log = LoggerFactory.getLogger(ATHROW.class);

    public void execute(Frame frame){
        SoObject ex = frame.getOperandStack().popObj();
        if(ex == null){
            NativeMethodUtil.panic("java.lang.NullPointerException");
        }

        SoThread thread = frame.getThread();
        if(findAndGotoExceptionHandler(thread, ex)){
            handleUncaughtExceptin(thread, ex);

        }
    }

    private boolean findAndGotoExceptionHandler(SoThread thread, SoObject ex){
        for(;;){
            Frame frame = thread.getCurrentFrame();
            int pc = frame.getNextPC() - 1;

            int handlerPc = frame.getMethod().findExceptionHandler(ex.getSoClass(), pc);
            if(handlerPc > 0){
                OperandStack stack = frame.getOperandStack();
                stack.clear();
                stack.pushObj(ex);
                frame.setNextPC(handlerPc);
                return true;
            }

            thread.popFrame();

            if(thread.isStackEmpty()){
                break;
            }
        }
        return false;
    }

    private void handleUncaughtExceptin(SoThread thread, SoObject ex){
        thread.clearStack();

        SoObject jMsg = ex.getRefVar("detailMessage","Ljava/lang/String");
        String goMsg = jMsg.toString();
        log.error(ex.getSoClass().javaName() + ": " + goMsg);

        log.error("\tat"+ex.getExtra().toString());
    }
}
