package hdfssim;

/**
 * Created by Amemiya on 5/13/15.
 */
public class WriteReplicaRequest extends Block {
    long trackID;
    public WriteReplicaRequest(Block block, long trackID){
        super(block);
        this.trackID=trackID;
    }

    public long getTrackID() {
        return trackID;
    }
    public double consumption(double speed){
        return this.getSize()/speed;
    }
}
