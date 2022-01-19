import Entity.Driver;
import Entity.User;
import Repository.DriverRepository;
import Repository.UserRepository;
import Service.CabService;
import BookingException.BookingException;

import java.util.HashMap;
import java.util.List;

public class BookCab {
    private CabService cabService ;

    public BookCab(){
        cabService = new CabService();
    }

    public void addUser(String name, int age, char gender) throws BookingException {
        cabService.addUser(name, age, gender);
        System.out.println("User Added");
    }

    public void addDriver(String name, int age, char gender,String licenceNumber, int location_x, int location_y) throws BookingException {
        cabService.addDriver(name, age, gender, licenceNumber, location_x, location_y);
        System.out.println("Driver Added");
    }

    public List<String> findRide(String name, int source_x, int source_y, int dest_x, int dest_y) throws BookingException {
        boolean userStatus = cabService.getUserStatus(name);
        if(userStatus){
            throw new BookingException("User is already in ride");
        }

        // generate ticket for ride
        cabService.generateTicket(name,source_x,source_y,dest_x,dest_y);

        //get driver list to choose from by user
        List<String> driverList = cabService.findRide(source_x,source_y);
        if(driverList.size()!=0){
            System.out.println("Available Drivers :");
            System.out.println(driverList);
        }
        else{
            System.out.println("No Drivers Available");
        }
        return driverList;
    }

    public void chooseRide(String user, String driver) throws BookingException {
        cabService.chooseRide(user, driver);
        System.out.println("Ride has started");
    }


    public void updateUserLocation(String name , int x, int y) throws BookingException {
        cabService.updateUserLocation(name,x,y);
        System.out.println("User location updated successfully");
    }

    public void updateDriverLocation(String name , int x, int y) throws BookingException {
        cabService.updateDriverLocation(name,x,y);
        System.out.println("Driver location updated successfully");
    }

    public double calculateBill(String name) throws BookingException {
        double bill = cabService.calculateBill(name);
        System.out.println("Bill Amount is: "+ bill);
        return bill;
    }

    public HashMap<String,Double> findTotalEarnings() throws BookingException {
        System.out.println("Earnings by all drivers:- ");
        return cabService.findTotalEarnings();
    }

    public void changeDriverStatus(String name,boolean status) throws BookingException {
        cabService.changeDriverStatus(name,status);
    }
}

