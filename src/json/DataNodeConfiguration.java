package json;
import java.io.File;

/**
 * Created by Amemiya on 4/25/15.
 */
public class DataNodeConfiguration {
    private double baudRate;
    private String ipAddr;
    private long capacity,remaining;
    public DataNodeConfiguration(){

    }
    public double getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(double baudRate) {
        this.baudRate = baudRate;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getRemaining() {
        return remaining;
    }

    public void setRemaining(long remaining) {
        this.remaining = remaining;
    }
}
