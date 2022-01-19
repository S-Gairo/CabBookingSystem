package Service;

import BookingException.BookingException;
import Entity.Driver;
import Entity.Ticket;
import Entity.User;
import Repository.DriverRepository;
import Repository.TicketRepository;
import Repository.UserRepository;

import java.util.HashMap;
import java.util.List;

public class CabService {
    private UserRepository userRepository;
    private DriverRepository driverRepository;
    private TicketRepository ticketRepository;
    int priceMultiplier = 10;
    int maxDistance = 5;

    public CabService(){
        userRepository = new UserRepository();
        driverRepository = new DriverRepository();
        ticketRepository = new TicketRepository();
    }

    public void addUser(String name, int age, char gender) throws BookingException {
        User user = new User();
        user.setName(name);
        if(userRepository.userExists(name)){
            throw new BookingException("User already exists");
        }
        user.setAge(age);
        user.setGender(gender);

        userRepository.addUser(user);
    }

    public void addDriver(String name, int age, char gender,String licenceNumber, int location_x, int location_y) throws BookingException {
        Driver driver = new Driver();
        driver.setName(name);
        if(driverRepository.driverExists(name)){
            throw new BookingException("Driver already exists");
        }
        driver.setAge(age);
        driver.setGender(gender);
        driver.setLicenceNumber(licenceNumber);
        driver.setLocation(location_x,location_y);
        driverRepository.addDriver(driver);
    }

    public List<String> findRide(int x, int y) {
        return driverRepository.findRide(x,y);
    }


    public void chooseRide(String user, String driver) throws BookingException {

        //get ticket
        Ticket ticket = ticketRepository.getTicket(user);
        boolean driverStatus = driverRepository.getDriverStatus(driver);
        boolean userStatus = userRepository.getUserStatus(user);

        if(ticket.getStatus() || driverStatus || userStatus){
            throw new BookingException("Ride already started or Unavailable");
        }

        //Validate driver
        int[] source = ticket.getSource();
        int source_x = source[0];
        int source_y = source[1];
        int[] driverLocation = driverRepository.getDriverLocation(driver);
        double distance = getDistance(source,driverLocation);
        if(distance>maxDistance){
            throw new BookingException("Invalid driver selected");
        }

        //select driver and send driver to user destination
        ticket.setDriver(driver);
        updateDriverLocation(driver,source_x,source_y);

        //start ride
        driverRepository.changeDriverStatus(driver,true);
        userRepository.changeUserStatus(user,true);
        ticketRepository.changeTicketStatus(user,true);
    }

    public void updateUserLocation(String name, int x, int y) throws BookingException {
        userRepository.updateUserLocation(name,x,y);
    }

    public void updateDriverLocation(String name, int x, int y) throws BookingException {
        driverRepository.updateDriverLocation(name,x,y);
    }

    public boolean getUserStatus(String name) throws BookingException {
        return userRepository.getUserStatus(name);
    }

    public double calculateBill(String name) throws BookingException {
        //get ticket.
        Ticket ticket = ticketRepository.getTicket(name);
        boolean ticketStatus = ticket.getStatus();
        if(!ticketStatus){
            throw new BookingException("Ride has already ended");
        }
        String driverName = ticket.getDriver();
        //calculate price.
        int[] source = ticket.getSource();
        int[] destination = ticket.getDestination();
        double distance = getDistance(source,destination);
        double price = distance*priceMultiplier;
        driverRepository.addEarnings(driverName,price);

        //change status of driver, user, ticket.
        driverRepository.changeDriverStatus(driverName,false);
        userRepository.changeUserStatus(name,false);
        ticketRepository.changeTicketStatus(name,false);

        // change user and driver location
        int x = destination[0], y = destination[1];
        updateDriverLocation(driverName,x,y);
        updateUserLocation(name,x,y);
        return price;
    }

    private double getDistance(int[] source, int[] destination) {
        double distanceSq = Math.pow(source[0]-destination[0],2) + Math.pow(source[1]-destination[1],2);
        return Math.sqrt(distanceSq);
    }

    public void generateTicket(String name, int source_x,int source_y,int destination_x,int destination_y){
        ticketRepository.generateTicket(name,source_x,source_y,destination_x,destination_y);
    }

    public HashMap<String,Double> findTotalEarnings() {
        return driverRepository.findTotalEarnings();
    }

    public void changeDriverStatus(String name, boolean status) throws BookingException {
        driverRepository.changeDriverStatus(name,status);
    }
}
