package hou.xxx.testy;


import java.io.NotActiveException;

public class RequestSimulationThread implements Runnable{

    @Override
    public void run() {
        System.out.println(" RequestSimulationThread :: ---- EXECUTED ---- ");
        while (true) {
            TestSubclass testObject = new TestSubclass();
            System.out.println("--------------------:: method execution START ::-------------------");
            //testObject.methodA();
            //testObject.methodFromAbstractClass();
            try {
                testObject.methodC(665,1);
            } catch (NotActiveException e) {
                System.out.println(" EXCEPTION FINALLY RETHROWN TO ORYGINAL CODE ");
                e.printStackTrace();
            }
            System.out.println("--------------------:: method execution END ::---------------------");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
