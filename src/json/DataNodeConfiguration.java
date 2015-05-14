package json;

import java.util.ArrayList;

/**
 * Created by Amemiya on 4/25/15.
 */
public class DataNodeConfiguration {
    private double baudRate;
    private String ipAddr;
    private long capacity, remaining;
    private ArrayList<DiskConfig> diskConfigs = new ArrayList<DiskConfig>();

    public DataNodeConfiguration(NodeConfig nc) {
        this.ipAddr = nc.getIpAddr();
        this.baudRate = nc.getBaudRate();
        this.diskConfigs = nc.getDiskConfigs();
    }

    public double getBaudRate() {
        return baudRate;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public ArrayList<DiskConfig> getDiskConfigs() {
        return diskConfigs;
    }
}
