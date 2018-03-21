package hou.xxx.testy;


import com.google.common.base.Stopwatch;
import javassist.*;
import javassist.util.HotSwapAgent;
import javassist.util.HotSwapper;
import org.apache.commons.lang3.ClassUtils;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.*;

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



    private Map<Class<?>, Set<Class<?>>> scanClasses(Class[] classes) {
        Map<Class<?>, Set<Class<?>>> parent2chilrden = new HashMap<>();
        long stopwatch = new Date().getTime();
        for(Class clazz : classes) {
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(clazz);
            List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(clazz);

            // parent 2 children
            for (Class item : interfaces) {
                Set<Class<?>> set = parent2chilrden.get(item);
                if (set == null) {
                    set = new HashSet<Class<?>>();
                }
                set.add(clazz);
                parent2chilrden.put(item, set);
            }
            for(Class item : superclasses) {
                Set<Class<?>> set = parent2chilrden.get(item);
                if (set == null) {
                    set = new HashSet<Class<?>>();
                }
                set.add(clazz);
                parent2chilrden.put(item, set);
            }
        }
        long end = new Date().getTime();
        System.out.println(" fullscan : " + (end - stopwatch));
        return parent2chilrden;
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

                // simple case - normal class
                String className = "hou.xxx.testy.TestClass";
                String methodName = "methodA";

                //method with two arguments
                //String className = "hou.xxx.testy.TestClass";
                //String methodName = "methodC";

                HotSwapAgent hsa = new HotSwapAgent();
                Stopwatch stopwatchA = Stopwatch.createStarted();
                Class[] classes = hsa.instrumentation().getAllLoadedClasses();
                Map<Class<?>, Set<Class<?>>> result = scanClasses(classes);

                System.out.println("#######################################################################");
                System.out.println("size: " + result.size());

                System.out.println("#######################################################################");
                System.out.println("#######################################################################");
                System.out.println(" scan for: " + InterfaceForAbstractClass.class.getName());
                result.get(InterfaceForAbstractClass.class).stream().forEach(System.out::println);

                System.out.println("#######################################################################");
                System.out.println("#######################################################################");
                System.out.println(" scan for: " + InterfaceA.class.getName());
                result.get(InterfaceA.class).stream().forEach(System.out::println);

                System.out.println("#######################################################################");
                System.out.println("#######################################################################");
                System.out.println(" scan for: " + AbstractTestClass.class.getName());
                result.get(AbstractTestClass.class).stream().forEach(System.out::println);

                System.out.println("#######################################################################");
                System.out.println("#######################################################################");
                System.out.println(" scan for: " + TestClass.class.getName());
                result.get(TestClass.class).stream().forEach(System.out::println);

                System.out.println("#######################################################################");
                System.out.println("#######################################################################");
                System.out.println(" scan for: " + Object.class.getName());
                result.get(Object.class).stream().forEach(System.out::println);

                System.out.println("#######################################################################");
                System.out.println("#######################################################################");
                stopwatchA.stop();
                System.out.println(" -------------------------------------------- whole scan + allClasses = " + stopwatchA.elapsed() + " ---");




                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Long start = new Date().getTime();
                Class source = Class.forName(className);

                pool.insertClassPath(new LoaderClassPath(source.getClassLoader()));


                cc = pool.get(className);
                CtMethod method = cc.getDeclaredMethod(methodName);
                
                cc.stopPruning(true);
                cc.defrost();
                // modifications

                method = add_timing(method);
                //method = add_arguments_list(method);
                //method = add_exception_catch(method);

                HotSwapAgent.redefine(source, cc);
                cc.detach();
                Long end = new Date().getTime();
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> :: SWAP = DONE ["+value+"] :: timing = " + (end-start) + "ms");
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
