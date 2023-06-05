public class Main {
    public static void main(String[] args) {
        int maxNumber = 20;
        Thread oddThread = new Thread(new NumberPrinter(maxNumber,false,ThreadColor.ANSI_CYAN),"Odd Number");
        Thread evenThread = new Thread(new NumberPrinter(maxNumber,true,ThreadColor.ANSI_RED),"Even Number");
        oddThread.start();
        evenThread.start();

    }
}