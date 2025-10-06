class PerformOperation {
    boolean turn = true; 

    public void printEven(String even) {
        Runnable r1 = () -> {
            synchronized (this) {
                for (int i = 1; i <= 100; i++) {
                    while (turn) { 
                        try {wait();} catch (InterruptedException e) { e.printStackTrace();}
                    }
                    if (i % 2 == 0)
                        System.out.println(Thread.currentThread().getName()+" "+ i);

                    turn = true; 
                    notify(); 
                } } };
        Thread t1 = new Thread(r1, even);
        t1.start();
    }

    public void printOdd(String odd) {
        Runnable r2 = () -> {
            synchronized (this) {
                for (int i = 1; i <= 100; i++) {
                    while (!turn) { 
                        try {wait();} catch (InterruptedException e) { e.printStackTrace();}
                    }
                    if (i % 2 == 1)
                        System.out.println(Thread.currentThread().getName()+" "+i);

                    turn = false; 
                    notify(); 
                }  } };

        Thread t1 = new Thread(r2, odd);
        t1.start();
    }
}

class EvenOddTask {
    public static void main(String[] args) {
        PerformOperation p = new PerformOperation();
        p.printEven("Even ");
        p.printOdd("Odd ");
    }
}
