package Repository;

import Entity.Driver;
import Entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRepository {
    private HashMap<String,User> userList;
    public UserRepository(){
        userList = new HashMap<>();
    }

    public HashMap<String, User> getUserList() {
        return userList;
    }

    public void setUserList(HashMap<String, User> userList) {
        this.userList = userList;
    }

    public void addUser(User user){
        this.userList.put(user.getName(),user);
    }

    public int[] getUserLocation(String name){
        User user = userList.get(name);
        return user.getLocation();
    }

    public void updateUserLocation(String name, int x, int y) {
        User user = userList.get(name);
        user.setLocation(x,y);
    }

    public void changeUserStatus(String name, boolean status) {
        User user = userList.get(name);
        user.setStatus(status);
    }

    public boolean getUserStatus(String name) {
        User user = userList.get(name);
        return user.getStatus();
    }
}
