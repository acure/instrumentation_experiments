package hou.xxx.testy;


public class RequestSimulationThread implements Runnable{

    @Override
    public void run() {
        System.out.println(" RequestSimulationThread :: ---- EXECUTED ---- ");
        while (true) {
            TestSubclass testObject = new TestSubclass();
            System.out.println("--------------------:: method A execution START ::-------------------");
            //testObject.methodA();
            //testObject.methodFromAbstractClass();
            testObject.methodC(13,23);
            System.out.println("--------------------:: method A execution END ::---------------------");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
