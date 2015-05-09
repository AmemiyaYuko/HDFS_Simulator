package hdfssim;

/**
 * Created by Amemiya on 4/23/15.
 */
public class BlockManager {
    //如果要加入磁盘读写的话可以在BlockID中加入File然后这里加入read
    BlockID blockID;
    BlockInfo blockInfo;
    byte[] data;
    BlockManager(long i,long size) {
        blockID = new BlockID(i);
        blockInfo = new BlockInfo(blockID,size);
    }
    BlockManager(long i){
        blockID=new BlockID(i);
        blockInfo=new BlockInfo(blockID,64);
    }

    public BlockID getBlockID() {
        return blockID;
    }

    public void setBlockID(BlockID blockID) {
        this.blockID = blockID;
    }

    public BlockInfo getBlockInfo() {
        return blockInfo;
    }

    public void setBlockInfo(BlockInfo blockInfo) {
        this.blockInfo = blockInfo;
    }

    public long read(String IPAddr){
        return 0;
    }
}
