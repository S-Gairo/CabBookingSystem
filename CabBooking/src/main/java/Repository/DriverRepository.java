package Repository;

import Entity.Driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverRepository {
    private HashMap<String, Driver> driverList;
    private HashMap<String,Double> earnings;
    int maxDistance = 5;

    public DriverRepository() {
       driverList = new HashMap<>();
       earnings = new HashMap<>();
    }

    public HashMap<String, Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(HashMap<String, Driver> driverList) {
        this.driverList = driverList;
    }

    public HashMap<String, Double> getEarnings() {
        return earnings;
    }

    public void setEarnings(HashMap<String, Double> earnings) {
        this.earnings = earnings;
    }

    public void addEarnings(String name, double amount){
        if(!earnings.containsKey(name)){
            //error
        }
        double total = earnings.get(name)+amount;
        earnings.put(name,total);

    }
    public void createEarnings(String name){
        earnings.put(name,0.0);
    }

    public void addDriver(Driver driver){
        this.driverList.put(driver.getName(),driver);
    }

    public List<String> findRide(int x, int y) {
        List<String> list = new ArrayList<>();
        driverList.forEach((name,driver) -> {
            if( !driver.getStatus()){
                int[] location = driver.getLocation();
                double distance = getDistance(x,y,location);
                if(distance <= maxDistance) {
                    list.add(name);
                }
            }
        });
        return list;
    }

    private double getDistance(int x, int y, int[] destination) {
        double distanceSq = Math.pow(x-destination[0],2) + Math.pow(y-destination[1],2);
        return Math.sqrt(distanceSq);
    }

    public void updateDriverLocation(String name, int x, int y) {
        Driver driver = driverList.get(name);
        driver.setLocation(x,y);
    }

    public void changeDriverStatus(String name, boolean status) {
        Driver driver = driverList.get(name);
        driver.setStatus(status);
    }

    public boolean getDriverStatus(String name){
        return driverList.get(name).getStatus();
    }

    public void findTotalEarnings() {
        earnings.forEach((k,v) -> System.out.println("Driver " + k + "earned Rs." + v));
    }
}
