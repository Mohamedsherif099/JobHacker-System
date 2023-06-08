import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketBooking {
    private int availableSeats;
    private int totalSeats;

    private Lock lock;

    public TicketBooking(int totalSeats) {
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.lock = new ReentrantLock(true);
    }

    public boolean bookTicket(int seats){
        lock.lock();
        try {
            if (availableSeats>=seats){
                this.availableSeats -= seats;
                return true;
            }else {
                return false;
            }

        }finally {
            lock.unlock();
        }

    }


    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
}
 class User implements Runnable{

    TicketBooking ticketBooking;
    String threadColor;

    int seatsNum;

     public User(TicketBooking ticketBooking, String threadColor, int seatsNum) {
         this.ticketBooking = ticketBooking;
         this.threadColor = threadColor;
         this.seatsNum = seatsNum;
     }

     @Override
    public void run() {
        if (ticketBooking.bookTicket(seatsNum)){
            System.out.println(threadColor+Thread.currentThread().getName()+" booked "+ seatsNum+
                    ((seatsNum>1)?" seats":" seat"));
        }else {
            int seatsAvailable = seatsNum-ticketBooking.getAvailableSeats();
            System.out.println(threadColor+Thread.currentThread().getName()+" failed to book "+ seatsNum+
                    ((seatsNum>1)?" seats":" seat") + " only " + seatsAvailable+((seatsAvailable>1)?" seats":" seat") +" available");
        }
    }
}

