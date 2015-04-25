package json;
import java.io.File;

/**
 * Created by Amemiya on 4/25/15.
 */
public class DataNodeConfiguration {
    private static double baudRate;
    private static int DataNodeID;
    private static String ipAddr,hostName,inPortName,outPortName;
    static long capacity,remaining;
    File jsonFile;
    DataNodeConfiguration(String fileName){
        jsonFile=new File(fileName);
    }
    public static double getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(double baudRate) {
        this.baudRate = baudRate;
    }

    public static int getDataNodeID() {
        return DataNodeID;
    }

    public static void setDataNodeID(int dataNodeID) {
        DataNodeID = dataNodeID;
    }

    public static String getIpAddr() {
        return ipAddr;
    }

    public static void setIpAddr(String ipAddr) {
        DataNodeConfiguration.ipAddr = ipAddr;
    }

    public static String getHostName() {
        return hostName;
    }

    public static void setHostName(String hostName) {
        DataNodeConfiguration.hostName = hostName;
    }

    public static String getInPortName() {
        return inPortName;
    }

    public static void setInPortName(String inPortName) {
        DataNodeConfiguration.inPortName = inPortName;
    }

    public static String getOutPortName() {
        return outPortName;
    }

    public static void setOutPortName(String outPortName) {
        DataNodeConfiguration.outPortName = outPortName;
    }

    public static long getCapacity() {
        return capacity;
    }

    public static void setCapacity(long capacity) {
        DataNodeConfiguration.capacity = capacity;
    }

    public static long getRemaining() {
        return remaining;
    }

    public static void setRemaining(long remaining) {
        DataNodeConfiguration.remaining = remaining;
    }
}
