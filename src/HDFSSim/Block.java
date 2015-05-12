package hdfssim;

import java.util.ArrayList;

/**
 * Created by Amemiya on 4/23/15.
 */
public class Block {
    long blockID;
    long size;
    // replicas of this block were stored in following datanode list
    ArrayList<DataNodeInfo> dataNodeList=new ArrayList<DataNodeInfo>();


    Block(long blockID, long size) {
        this.blockID=blockID;
        this.size=size;
    }

    Block(long blockID, long size, ArrayList<DataNodeInfo> dataNodeList){
        this.blockID=blockID;
        this.size=size;
        this.dataNodeList=new ArrayList<DataNodeInfo>(dataNodeList);
    }

    public long getBlockID() {
        return this.blockID;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public int numOfReplicas(){
        return dataNodeList.size();
    }

    public ArrayList<DataNodeInfo> getDataNodeList() {
        return dataNodeList;
    }

    public boolean removeReplicatedDataNode(DataNodeInfo dataNodeInfo){
        return  this.dataNodeList.remove(dataNodeInfo);
    }

    public boolean addReplicatedDataNode(DataNodeInfo dataNodeInfo){
        return this.dataNodeList.add(dataNodeInfo);
    }
}
