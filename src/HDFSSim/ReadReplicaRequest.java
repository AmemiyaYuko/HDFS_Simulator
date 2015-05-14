package hdfssim;

/**
 * Created by Amemiya on 5/14/15.
 */
public class ReadReplicaRequest {
    private long trackID;
    private long blockID;
    private double offset;
    private double size;
    public ReadReplicaRequest(long blockID,long trackID,double offset){
        this.blockID=blockID;
        this.trackID=trackID;
        this.offset=offset;
    }
    public long getTrackID() {
        return trackID;
    }
    public long getBlockID(){
        return blockID;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double consumption(double speed,double seekTime){
        return ((size-offset)/speed+seekTime)*1000;
    }

    public void finish(){
        //Logger.
    }
}
