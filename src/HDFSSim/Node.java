package hdfssim;

/**
 * Created by Amemiya on 4/25/15.
 */
public class Node {
    private String ipAddr;
    private String hostName;
    private long generationStamp;
    public Node(String ipAddr,String hostName){
        this.ipAddr=ipAddr;
        this.hostName=hostName;
        this.generationStamp=System.nanoTime()%1000000000;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public long getGenerationStamp() {
        return generationStamp;
    }

    public boolean equal(Node o){
        if ((this.hostName.equals(o.getHostName()))&&
                (this.ipAddr.equals(o.getIpAddr()))&&
                (this.generationStamp==o.getGenerationStamp())){
            return true;
        }
        return false;
    }
}
