class SharedObject {
    boolean flag = false;

    synchronized void printEven(int num) {
        while (!flag) {
            try {
                wait(); // Wait for the odd number thread to notify
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Even: " + num);
        flag = false;
        notify(); // Notify the odd number thread to proceed
    }

    synchronized void printOdd(int num) {
        while (flag) {
            try {
                wait(); // Wait for the even number thread to notify
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Odd: " + num);
        flag = true;
        notify(); // Notify the even number thread to proceed
    }
}

class EvenThread extends Thread {
    SharedObject shared;
    int max;

    EvenThread(SharedObject shared, int max) {
        this.shared = shared;
        this.max = max;
    }

    public void run() {
        for (int i = 2; i <= max; i += 2) {
            shared.printEven(i);
        }
    }
}

class OddThread extends Thread {
    SharedObject shared;
    int max;

    OddThread(SharedObject shared, int max) {
        this.shared = shared;
        this.max = max;
    }

    public void run() {
        for (int i = 1; i <= max; i += 2) {
            shared.printOdd(i);
        }
    }
}

public class Prog19 {
    public static void main(String[] args) {
        SharedObject shared = new SharedObject();
        int max = 10;

        Thread evenThread = new EvenThread(shared, max);
        Thread oddThread = new OddThread(shared, max);

        evenThread.start();
        oddThread.start();
    }
}
