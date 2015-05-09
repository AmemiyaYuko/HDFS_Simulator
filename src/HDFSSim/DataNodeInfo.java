package hdfssim;

import json.DataNodeConfiguration;

import java.util.HashMap;

/**
 * Created by Amemiya on 4/23/15.
 */
public class DataNodeInfo extends Node{
    private String inPortName;
    private String outPortName;
    private long capacity;
    private long remaining;
    private HashMap<BlockID,BlockManager> blocksMap;

    DataNodeInfo(String ipAddr,String hostName,String inPortName,String outPortName,long capacity,long remaining){
        super(new String(ipAddr),new String(hostName));
        this.inPortName=new String(inPortName);
        this.outPortName=new String(outPortName);
        this.capacity=capacity;
        this.remaining=remaining;
        this.blocksMap=new HashMap<BlockID, BlockManager>();
    }

    DataNodeInfo(DataNodeInfo dataNodeInfo){
        super(new String(dataNodeInfo.getIpAddr()),new String(dataNodeInfo.getHostName()));
        this.inPortName=new String(dataNodeInfo.getInPortName());
        this.outPortName=new String(dataNodeInfo.getOutPortName());
        this.capacity=dataNodeInfo.getCapacity();
        this.remaining=dataNodeInfo.getRemaining();
        this.blocksMap=new HashMap<BlockID, BlockManager>(dataNodeInfo.getBlocksMap());
    }

    DataNodeInfo(String ipAddr,String hostName,String inPortName,String outPortName,long capacity){
        super(new String(ipAddr),new String(hostName));
        this.inPortName=new String(inPortName);
        this.outPortName=new String(outPortName);
        this.capacity=capacity;
        this.remaining=capacity;
        this.blocksMap=new HashMap<BlockID, BlockManager>();
    }
    DataNodeInfo(DataNodeConfiguration dataNodeConfiguration){
        super(dataNodeConfiguration.getIpAddr(),dataNodeConfiguration.getHostName());
        this.inPortName=new String(dataNodeConfiguration.getInPortName());
        this.outPortName=new String(dataNodeConfiguration.getOutPortName());
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
