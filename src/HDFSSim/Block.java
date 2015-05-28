package hdfssim;

/**
 * Created by Amemiya on 4/23/15.
 */
public class Block {
    private Long blockID;
    private double size;//kb

    public Block(Long blockID, double size) {
        this.blockID = blockID;
        this.size = size;
        // this.dataNodeIPList = new ArrayList<String>(dataNodeList);
    }

    Block(Block block) {
        this.blockID = block.getBlockID();
        this.size = block.getSize();
        // this.dataNodeIPList = this.getDataNodeIPList();
    }

    public long getBlockID() {
        return blockID;
    }

    public double getSize() {
        return size;
    }


    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        Long l = (Long) o;
        return o.equals(blockID);
    }

    @Override
    public int hashCode() {
        Long l = new Long(blockID);
        return l.hashCode();
    }

    @Override
    public String toString() {
        return blockID.toString();
    }

}
