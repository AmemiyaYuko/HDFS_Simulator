package HDFSSim;

import java.util.Comparator;

/**
 * Created by Amemiya on 4/23/15.
 */
public class BlockID {
    private long id;
    BlockID(long id){
        this.id=id;
    }
    public long getId(){
        return this.id;
    }


    public boolean equal(BlockID o) {
        if (o.getId()==this.id) return true;
        return false;
    }
}
