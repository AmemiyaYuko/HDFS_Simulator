package core;

/**
 * Created by Amemiya on 5/13/15.
 */
public class HDDID {
    private String ipAddr;
    private String id;

    public HDDID(String ipAddr, String id) {
        this.ipAddr = ipAddr;
        this.id = id;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        HDDID hddid = (HDDID) o;
        return (hddid.getIpAddr().equals(this.ipAddr)) && (hddid.getId().equals(this.id));
    }

    @Override
    public int hashCode() {
        String tmp = new String(this.ipAddr + this.id);
        return tmp.hashCode();
    }

    @Override
    public String toString() {
        return new String(this.ipAddr + this.id);
    }

}
