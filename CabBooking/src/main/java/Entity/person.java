package Entity;

public class person {
    private String name;
    private int age;
    private char gender;
    private int[] location = new int[2];
    boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        location[0] = x;
        location[1] = y;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
