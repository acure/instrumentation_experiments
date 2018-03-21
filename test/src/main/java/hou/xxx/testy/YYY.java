package hou.xxx.testy;

import org.apache.commons.lang3.ClassUtils;

import java.util.List;

/**
 * Created by Ant on 2018-03-21.
 */
public class YYY {

    public static void main(String[] args) {
        Class testClass = TestClass.class;
        Class testSubClass = TestSubclass.class;

        List<Class<?>> interfaces = ClassUtils.getAllInterfaces(testSubClass);
        List<Class<?>> parents = ClassUtils.getAllSuperclasses(testSubClass);

        interfaces.stream().forEach(System.out::println);
        System.out.println("--------");
        parents.stream().forEach(System.out::println);

        System.out.println("superClass for TestClass : " + testClass.getSuperclass().getName());
        System.out.println("superClass for TestSubClass : " + testClass.getSuperclass().getName());

    }
}
