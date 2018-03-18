package hou.xxx.testy;

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

    public int methodC(int a, int b) {
        System.out.println("TestClass.methodC (" + a +", " + b +")");
        return a + b;
    }

    public void methodFromInterfaceForAbstractClass() {
        System.out.println("TestClass.methodFromInterfaceForAbstractClass");
    }
}
