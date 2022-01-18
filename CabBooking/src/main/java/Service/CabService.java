package Service;

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

    public CabService(){
        userRepository = new UserRepository();
        driverRepository = new DriverRepository();
        ticketRepository = new TicketRepository();
    }

    public void addUser(){
        User user = new User();
        // add attributes
        if(userRepository.getUserList().containsKey(user.getName())){
            //error - user exists
        }
        userRepository.addUser(user);
    }

    public void addDriver() {
        Driver driver = new Driver();
        //set corresponding values
        if(driverRepository.getDriverList().containsKey(driver.getName())){
            //error - driver exists
        }
        driverRepository.addDriver(driver);
        driverRepository.createEarnings(driver.getName());
    }

    public List<String> findRide(int x, int y) {
        return driverRepository.findRide(x,y);
    }


    public void chooseRide(String user, String driver) {
        //get ticket
        Ticket ticket = ticketRepository.getTicket(user);
        boolean driverStatus = driverRepository.getDriverStatus(driver);
        boolean userStatus = userRepository.getUserStatus(user);

        if(ticket.getStatus() || driverStatus || userStatus){
            //error - entity/entities already in ride
        }

        //select driver and send driver to user destination
        ticket.setDriver(driver);
        int[] source = ticket.getSource();
        int source_x = source[0];
        int source_y = source[1];
        updateDriverLocation(driver,source_x,source_y);

        //start ride
        driverRepository.changeDriverStatus(driver,true);
        userRepository.changeUserStatus(user,true);
        ticketRepository.changeTicketStatus(user,true);
    }

    public void updateUserLocation(String name, int x, int y) {
        userRepository.updateUserLocation(name,x,y);
    }

    public void updateDriverLocation(String name, int x, int y) {
        driverRepository.updateDriverLocation(name,x,y);
    }

    public boolean getUserStatus(String name) {
        return userRepository.getUserStatus(name);
    }

    public double calculateBill(String name) {
        //get ticket.
        Ticket ticket = ticketRepository.getTicket(name);
        boolean ticketStatus = ticket.getStatus();
        if(!ticketStatus){
            // ride has already ended
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
        return price;
    }

    private double getDistance(int[] source, int[] destination) {
        double distanceSq = Math.pow(source[0]-destination[0],2) + Math.pow(source[1]-destination[1],2);
        return Math.sqrt(distanceSq);
    }

    public void generateTicket(String name, int source_x,int source_y,int destination_x,int destination_y) {
        ticketRepository.generateTicket(name,source_x,source_y,destination_x,destination_y);
    }

    public void findTotalEarnings() {
        driverRepository.findTotalEarnings();
    }
}
