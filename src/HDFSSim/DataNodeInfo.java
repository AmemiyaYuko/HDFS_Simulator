package hdfssim;

import eduni.simjava.Sim_entity;
import json.DataNodeConfiguration;

import java.util.HashMap;

/**
 * Created by Amemiya on 4/23/15.
 */
public class DataNodeInfo extends Sim_entity{
    private String inPortName,outPortName;
    private long capacity;
    private long remaining;
    private HashMap<BlockID,BlockManager> blocksMap;

    DataNodeInfo(String ipAddr,long capacity,long remaining,String inPortName,String outPortName){
        super(ipAddr);
        this.capacity=capacity;
        this.remaining=remaining;
        this.blocksMap=new HashMap<BlockID, BlockManager>();
        this.inPortName=inPortName;
        this.outPortName=outPortName;
    }

    DataNodeInfo(String ipAddr,long capacity){
        super(ipAddr);
        this.capacity=capacity;
        this.remaining=capacity;
        this.blocksMap=new HashMap<BlockID, BlockManager>();
    }
    DataNodeInfo(DataNodeConfiguration dataNodeConfiguration){
        super(dataNodeConfiguration.getIpAddr());
        this.capacity=dataNodeConfiguration.getCapacity();
        this.remaining=dataNodeConfiguration.getRemaining();
        this.blocksMap=new HashMap<BlockID, BlockManager>();
    }

    public String getInPortName() {
        return inPortName;
    }

    public void setInPortName(String inPortName) {
        this.inPortName = new String(inPortName);
    }

    public String getOutPortName() {
        return outPortName;
    }

    public void setOutPortName(String outPortName) {
        this.outPortName = new String(outPortName);
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getRemaining() {
        return remaining;
    }

    public void setRemaining(long remaining) {
        this.remaining = remaining;
    }

    public HashMap<BlockID, BlockManager> getBlocksMap() {
        return blocksMap;
    }

    public boolean addBlockToMap(BlockID blockID,BlockManager blockManager){
        this.blocksMap.put(blockID,blockManager);
        return true;
    }

    public boolean removeFromBlocksMap(BlockID blockID){
        return this.blocksMap.remove(blockID,this.blocksMap.get(blockID));
    }

    public int numOfHostedBlock(){
        return blocksMap.size();
    }

    public BlockManager getBlockManager(BlockID blockID){
        return blocksMap.get(blockID);
    }

}
