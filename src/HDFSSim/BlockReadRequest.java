package hdfssim;

/**
 * Created by Amemiya on 5/13/15.
 */
public class BlockReadRequest extends Block{
    private long offset=0; //kb
    private int trackID;
    private BlockReadRequest(Block block, long offset, int trackID) {
        super(block);
        this.offset=offset;
        this.setSize(this.getSize()-offset);
        this.trackID=trackID;
    }

    public int getTrackID() {
        return trackID;
    }
}
