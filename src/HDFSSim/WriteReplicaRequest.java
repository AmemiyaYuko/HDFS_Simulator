package hdfssim;

import logger.Logger;

/**
 * Created by Amemiya on 5/13/15.
 */
public class WriteReplicaRequest extends Block {
    int trackID;

    public WriteReplicaRequest(Block block, int trackID) {
        super(block);
        this.trackID = trackID;
    }

    public int getTrackID() {
        return trackID;
    }

    public double consumption(double speed) {
        return this.getSize() / speed * 1000;// 1ms per sim_system time unit
    }

    public void finish(double clock) {
        Logger.newEvent(this.trackID, "Finished.", clock);
    }
}
