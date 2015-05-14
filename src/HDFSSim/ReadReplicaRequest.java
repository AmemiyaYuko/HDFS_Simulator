package hdfssim;

/**
 * Created by Amemiya on 5/14/15.
 */
public class ReadReplicaRequest extends Block {
    private long trackID;
    ReadReplicaRequest(Block block,long trackID){
        super(block);
        this.trackID=trackID;
    }
    public long getTrackID() {
        return trackID;
    }
    public double consumption(double speed){
        return this.getSize()/speed;
    }
    public void finish(){
        //Logger.
    }
}
