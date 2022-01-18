import BookingException.BookingException;
import Entity.User;

public class Tester {
    public static void main(String[] args) throws BookingException {
        BookCab app = new BookCab();
        app.addUser();
        app.addDriver();
        app.updateUserLocation();
        app.updateDriverLocation();
        app.findRide();
        app.chooseRide();
        app.calculateBill();
        app.findTotalEarnings();
    }
}
