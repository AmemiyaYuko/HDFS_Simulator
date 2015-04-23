package HDFSSim;

import java.util.Comparator;

/**
 * Created by Amemiya on 4/23/15.
 */
public class BlockID implements Comparable<BlockID> {
    private long id;
    BlockID(long id){
        this.id=id;
    }
    public long getId(){
        return this.id;
    }

    @Override
    public int compareTo(BlockID o) {
        if (o.getId()==this.getId()) return 1;
            else return 0;
    }
}
