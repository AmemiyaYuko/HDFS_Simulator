package json;
import java.io.File;

/**
 * Created by Amemiya on 4/25/15.
 */
public class DataNodeConfiguration {
    private double baudRate;
    private int DataNodeID;
    private String ipAddr,hostName,inPortName,outPortName;
    private long capacity,remaining;
    File jsonFile;
    public DataNodeConfiguration(String fileName){
        jsonFile=new File(fileName);
    }
    public double getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(double baudRate) {
        this.baudRate = baudRate;
    }

    public int getDataNodeID() {
        return DataNodeID;
    }

    public void setDataNodeID(int dataNodeID) {
        DataNodeID = dataNodeID;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getInPortName() {
        return inPortName;
    }

    public void setInPortName(String inPortName) {
        this.inPortName = inPortName;
    }

    public String getOutPortName() {
        return outPortName;
    }

    public void setOutPortName(String outPortName) {
        this.outPortName = outPortName;
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
