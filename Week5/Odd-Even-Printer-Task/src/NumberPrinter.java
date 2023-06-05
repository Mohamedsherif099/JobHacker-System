import com.sun.org.apache.xpath.internal.operations.Bool;

public class NumberPrinter implements Runnable {
    private static  volatile int currentNumber = 1 ;
    private int maxNumber;
    private Boolean isEven;
    private String threadColor;


    private static final Object lock = new Object();

    public NumberPrinter(int maxNumber, Boolean isEven, String threadColor) {
        this.maxNumber = maxNumber;
        this.isEven = isEven;
        this.threadColor = threadColor;
    }

    @Override
    public void run() {
        while(currentNumber<=maxNumber){
            synchronized (lock){
                if ((currentNumber%2==0)==isEven){
                    System.out.println(threadColor+Thread.currentThread().getName()+": "+currentNumber);
                    currentNumber++;
                    lock.notifyAll();

                }else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }


    }
}
