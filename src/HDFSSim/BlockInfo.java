package HDFSSim;

import jdk.nashorn.internal.ir.Block;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Amemiya on 4/23/15.
 */
public class BlockInfo  {
    BlockID blockID;
    ArrayList<DataNodeInfo> dataNodeList=new ArrayList<DataNodeInfo>();


    BlockInfo(BlockID blockID) {
        this.blockID=blockID;
    }
    BlockInfo(BlockID blockID,ArrayList<DataNodeInfo> dataNodeList){
        this.blockID=blockID;
        this.dataNodeList=new ArrayList<DataNodeInfo>(dataNodeList);
    }

    public BlockID getBlockID() {
        return this.blockID;
    }

    public int numOfReplicas(){
        return dataNodeList.size();
    }

    public ArrayList<DataNodeInfo> getDataNodeList() {
        return dataNodeList;
    }
}
