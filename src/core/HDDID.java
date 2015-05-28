package core;

/**
 * Every HDD has an unique HDDID. HDDID is a combination of IP address and disk ID.
 * Method equals , hashCode and toString are overrided in this class, so that HDDID
 * could be considered as key for HashMap
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
