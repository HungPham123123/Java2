package lab01;

public class mthread extends Thread {
    public mthread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("New thread = " + Thread.currentThread());

        for (int i = 5; i >= 1; i--) {
            System.out.println(getName() + " = " + i);
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(getName() + " exiting.");
    }
    public static void main(String[] args) {

        Thread firstThread = new mthread("First");
        Thread secondThread = new mthread("Second");
        Thread thirdThread = new mthread("Third");

        firstThread.start();
        secondThread.start();
        thirdThread.start();

        try {

            firstThread.join();
            secondThread.join();
            thirdThread.join();


            System.out.println("Main thread exiting.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

