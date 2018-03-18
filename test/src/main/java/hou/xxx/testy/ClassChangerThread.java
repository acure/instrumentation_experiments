package hou.xxx.testy;


import javassist.*;
import javassist.util.HotSwapAgent;
import javassist.util.HotSwapper;

import java.io.IOException;
import java.util.Date;

public class ClassChangerThread implements Runnable {
    private int value = 0;
    private ClassPool pool;

    public ClassChangerThread(ClassPool pool) {
        this.pool = pool;
    }

    private CtMethod add_sysout_value(CtMethod method) throws CannotCompileException {
        method.insertBefore("{ System.out.println(\"#### INJECTED :: [value = " + value + "]\"); }");
        return method;
    }

    private CtMethod add_arguments_list(CtMethod method) throws CannotCompileException {
        StringBuilder builder = new StringBuilder();
        builder.append("{ System.out.print(\"#### INJECTED :: argument list : \"); ");
        builder.append(" for(int i=0; i<$args.length; i++) {System.out.print(\" arg[\"+i+\"] =\" + $args[i]); } ");
        builder.append(" System.out.println(\"\"); }");
        method.insertBefore(builder.toString());
        return method;
    }

    private CtMethod add_exception_catch(CtMethod method) throws CannotCompileException, NotFoundException {
        StringBuilder builder = new StringBuilder();
        for(CtClass eClass : method.getExceptionTypes()) {
            method.addCatch("{ System.out.println(\"#### INJECTED :: Exception thrown : \" + $e); throw $e;}",eClass);
        }
        return method;
    }

    private CtMethod add_timing(CtMethod method) throws CannotCompileException {
        method.addLocalVariable("_startTime", CtClass.longType);
        method.insertBefore("_startTime = System.nanoTime();");
        method.insertAfter("System.out.println(\"#### INJECTED :: Execution Duration "+ "(nano sec): \"+ (System.nanoTime() - _startTime) );");
        return method;
    }



    @Override
    public void run() {
        System.out.println(">>>>>>>>>>>>>>> ClassChangerThread ---- EXECUTED ---- ");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CHANGER STARTED <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        while(true) {
            value++;

            CtClass cc = null;
            try {
                Long start = new Date().getTime();
                // simple case - normal class
                //String className = "hou.xxx.testy.TestClass";
                //String methodName = "methodA";

                //method with two arguments
                String className = "hou.xxx.testy.TestClass";
                String methodName = "methodC";


                Class source = Class.forName(className);
                cc = pool.get(className);
                CtMethod method = cc.getDeclaredMethod(methodName);
                
                cc.stopPruning(true);
                cc.defrost();
                // modifications

                method = add_arguments_list(method);
                method = add_exception_catch(method);

                HotSwapAgent.redefine(source, cc);
                cc.detach();
                Long end = new Date().getTime();
                System.out.println(">>>>>>>>>>>>>>> :: SWAP = DONE ["+value+"] :: timing = " + (end-start) + "ms");
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(321);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
