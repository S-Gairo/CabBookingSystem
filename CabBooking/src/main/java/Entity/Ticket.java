package Entity;

public class Ticket {
    String driver;
    int[] source;
    int[] destination;
    boolean status;

    public Ticket(){
        source = new int[2];
        destination = new int[2];
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int[] getSource() {
        return source;
    }

    public void setSource(int x, int y) {
        source[0] = x;
        source[1] = y;
    }

    public int[] getDestination() {
        return destination;
    }

    public void setDestination(int x , int y) {
        destination[0] = x;
        destination[1] = y;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
