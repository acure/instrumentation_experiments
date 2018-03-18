package hou.xxx.testy;

import javassist.*;

import java.io.IOException;

/**
 * Created by Ant on 2018-03-16.
 */
public class XXXTest {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        pool.appendSystemPath();
        Loader l = new Loader(Thread.currentThread().getContextClassLoader(), pool);

        

        TestClass xxx = (TestClass) l.loadClass("hou.xxx.testy.TestClass").newInstance();
        xxx.methodA();
        TestClass testA = new TestClass();
        testA.methodA();
        CtClass cc = pool.get("hou.xxx.testy.TestClass");
        CtMethod method = cc.getDeclaredMethod("methodA");
        method.insertBefore("{ System.out.println(\" dupa dupa dupa cycki \"); }");
        cc.toClass();
        System.out.println("-------------------------------------------------");
        testA.methodA();
        System.out.println(":::::::::::::::::::::");
        TestClass test = new TestClass();
        test.methodA();

    }

}
