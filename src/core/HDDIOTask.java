package core;

/**
 * Created by Amemiya on 5/9/15.
 */
public class HDDIOTask {
    private long consumption = 0;
    private long id;
    private long trackID;
    private HDDID hddid;

    HDDIOTask(double size, double speed, double seekTime, long trackID, HDDID hddid) {
        this.trackID = trackID;
        this.hddid = hddid;
        this.consumption = (int) ((size / speed + seekTime) * 1000);// 1ms per time unit
    }

    public long getConsumption() {
        return consumption;
    }

    public long getId() {
        return id;
    }

    public long getTrackID() {
        return trackID;
    }

    public HDDID getHddid() {
        return hddid;
    }
}
