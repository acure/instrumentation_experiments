package hou.xxx.testy;


import javassist.ClassPool;

public class ExampleApplication {

    public static void main(String[] args) {
        System.out.println(" ExampleApplication - started - ");

        Thread simulationThread = new Thread(new RequestSimulationThread());
        simulationThread.start();

        Thread modificationThread = new Thread(new ClassChangerThread(ClassPool.getDefault()));
        modificationThread.start();

    }

}
