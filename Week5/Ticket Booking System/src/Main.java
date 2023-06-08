public class Main {
    public static void main(String[] args) {

        int totalSeats = 50;
        TicketBooking ticketBooking = new TicketBooking(totalSeats);

       Thread user1 = new Thread(new User(ticketBooking,ThreadColor.ANSI_RED,10),"User1");
        Thread user2 = new Thread(new User(ticketBooking,ThreadColor.ANSI_CYAN,10),"User2");
        Thread user3 = new Thread(new User(ticketBooking,ThreadColor.ANSI_GREEN,20),"User3");
        Thread user4 = new Thread(new User(ticketBooking,ThreadColor.ANSI_PURPLE,15),"User4");

        user1.start();
        user2.start();
        user3.start();
        user4.start();





    }
}