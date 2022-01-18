import Entity.Driver;
import Entity.User;
import Repository.DriverRepository;
import Repository.UserRepository;
import Service.CabService;
import BookingException.BookingException;
import java.util.List;

public class BookCab {
    private CabService cabService ;

    public BookCab(){
        cabService = new CabService();
    }

    public void addUser() {
        cabService.addUser();
        System.out.println("User Added");

    }

    public void addDriver() {
        cabService.addDriver();
        System.out.println("Driver Added");
    }

    public void findRide() {
        String name = "user";
        int source_x = 0;
        int source_y = 0;
        int destination_x = 0;
        int destination_y = 0;

        boolean userStatus = cabService.getUserStatus(name);
        if(userStatus){
            //error - user is already riding
        }

        // generate ticket for ride
        cabService.generateTicket(name,source_x,source_y,destination_x,destination_y);

        //get driver list to choose from by user
        List<String> list = cabService.findRide(source_x,source_y);
        if(list.size()!=0){
            System.out.println("Choose Driver :");
            System.out.println(list);
        }
        else{
            System.out.println("No Drivers Available");
        }
    }

    public void chooseRide() {
        String user = "user";
        String driver = "driver";
        //add attributes from input
        cabService.chooseRide(user, driver);
        System.out.println("Ride has started");
    }


    public void updateUserLocation() {
        String name = "user";
        int x=0;
        int y=0;
        cabService.updateUserLocation(name,x,y);
        System.out.println("User location updated successfully");
    }

    public void updateDriverLocation() {
        String name = "driver";
        int x=0;
        int y=0;
        cabService.updateDriverLocation(name,x,y);
        System.out.println("Driver location updated successfully");
    }

    public double calculateBill() {
        String username = "user";
        double bill = cabService.calculateBill(username);
        System.out.println("Bill Amount is: "+ bill);
        return bill;
    }

    public void findTotalEarnings() {
        System.out.println("Earnings by all drivers:- ");
        cabService.findTotalEarnings();
    }
}
