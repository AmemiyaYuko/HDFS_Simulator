package json;

import java.util.ArrayList;

/**
 * Created by Amemiya on 5/12/15.
 */
public class NodeConfig {
    private String ipAddr;
    private double baudRate;
    private ArrayList<DiskConfig> disks=new ArrayList<DiskConfig>();

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public double getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(double baudRate) {
        this.baudRate = baudRate;
    }
    public void addDiskConfig(DiskConfig dc){
        this.disks.add(disks.size(),dc);
    }
    public ArrayList<DiskConfig> getDiskConfigs(){
        return disks;
    }
    public int numberOfDisks(){
        return disks.size();
    }
}
