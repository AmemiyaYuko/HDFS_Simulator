package HDFSSim;

/**
 * Created by Amemiya on 4/23/15.
 */
public class BlockManager {
    BlockID blockID;
    BlockInfo blockInfo;
    BlockManager(long i){
        blockID=new BlockID(i);
        blockInfo=new BlockInfo(blockID);
    }
}
