package hou.xxx.testy;

import java.io.NotActiveException;

public class TestClass extends AbstractTestClass implements InterfaceA{

    public void methodFromInterfaceA() {
        System.out.println("TestClass.methodFromInterface");
    }

    public void methodFromAbstractClass() {
        System.out.println("TestClass.methodFromAbstractClass");
    }

    public void methodA(){
        System.out.println("TestClass.methodA");
    }

    public int methodB(){
        System.out.println("TestClass.methodB");
        return 1;
    }

    public int methodC(int a, int b) throws NotActiveException{
        System.out.println("TestClass.methodC (" + a +", " + b +")");
        if (a + b == 666) {
            throw new NotActiveException("a + b == 666");
        }
        return a + b;
    }

    public void methodFromInterfaceForAbstractClass() {
        System.out.println("TestClass.methodFromInterfaceForAbstractClass");
    }
}
