package lab01;

public class MyThread extends Thread {
    public MyThread() {
        // Set the thread's name to "myJavaThread"
        super("myJavaThread");
    }

    @Override
    public void run() {
        System.out.println("Current Thread Name: " + Thread.currentThread().getName());

        // Display the first 10 even numbers
        for (int i = 2; i <= 20; i += 2) {
            System.out.println("Even Number(số chẵn): " + i);
            try {
                Thread.sleep(1500); // Sleep for 1.5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
