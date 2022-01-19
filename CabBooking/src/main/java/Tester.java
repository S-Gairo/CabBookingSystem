import BookingException.BookingException;
import Entity.User;
public class Tester {
    public static void main(String[] args) throws BookingException {
        BookCab app = new BookCab();
        try {
            app.addUser("Abhishek", 23, 'M');
            app.addUser("Rahul", 29, 'M');
            app.addUser("Nandini", 22, 'F');
            app.updateUserLocation("Abhishek", 0, 0);
            app.updateUserLocation("Rahul", 10, 0);
            app.updateUserLocation("Nandini", 15, 6);
            app.addDriver("Driver1", 22, 'M', "Swift, KA-01-12345", 10, 1);
            app.addDriver("Driver2", 29, 'M', "Swift, KA-01-12346", 11, 1);
            app.addDriver("Driver3", 24, 'F', "Swift, KA-01-12347", 5, 3);
            app.updateDriverLocation("Driver3", 5, 5);
            app.findRide("Abhishek", 0, 0, 20, 1);
            app.findRide("Rahul", 10, 0, 15, 3);
            app.chooseRide("Rahul", "Driver1");
            app.calculateBill("Rahul");
            app.changeDriverStatus("Driver1", true);
            app.findRide("Nandini", 15, 6, 20, 4);
            app.updateDriverLocation("Driver2",16,3);
            app.findRide("Nandini", 15, 6, 20, 4);
            app.chooseRide("Nandini","Driver2");
            app.updateDriverLocation("Driver3",13,4);
            app.findRide("Rahul",10,0,20,4);
            app.chooseRide("Rahul","Driver3");
            app.calculateBill("Nandini");
            app.calculateBill("Rahul");
            app.findTotalEarnings();
        }catch (BookingException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
