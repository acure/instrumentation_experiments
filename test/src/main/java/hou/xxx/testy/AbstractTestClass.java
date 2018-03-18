package hou.xxx.testy;


public abstract class AbstractTestClass implements InterfaceForAbstractClass{

    public abstract void methodFromAbstractClass();

    public String abstractClassMethod(int a, String b) {
        return b + " :: " + a;
    }
}
