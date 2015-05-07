package HDFSSim;

import eduni.simjava.Sim_entity;
import gridsim.GridSim;
import json.ClusterConfiguration;
import json.DataNodeConfiguration;

/**
 * Created by Amemiya on 4/25/15.
 */
public class DataNode extends GridSim{
    private DataNodeInfo dataNodeInfo;
    private BlockReceiver blockReceiver;
    private int dataNodeID;
    DataNode(int DataNodeID,String ipAddr,String hostName,String inPortName,String outPortName,long capacity,long remaining) throws Exception {
        super(hostName);
        this.dataNodeID=DataNodeID;
        dataNodeInfo=new DataNodeInfo(ipAddr,hostName,inPortName,outPortName,capacity,remaining);
        blockReceiver=new BlockReceiver(new String(hostName+inPortName), ClusterConfiguration.getDataNodeConfiguration(dataNodeID).getBaudRate());
    }
    DataNode(DataNodeConfiguration dataNodeConfiguration) throws Exception {
        super(dataNodeConfiguration.getHostName());
        DataNodeConfiguration df=dataNodeConfiguration;
        this.dataNodeID=df.getDataNodeID();
        dataNodeInfo=new DataNodeInfo(df.getIpAddr(),df.getHostName(),df.getInPortName(),df.getOutPortName(),df.getCapacity(),df.getRemaining());
        blockReceiver=new BlockReceiver(new String(df.getHostName())+new String(df.getInPortName()),df.getBaudRate());
    }
    public void interDataNodeReadBlock(String receiverName, BlockID blockID,double baudRate) throws Exception {
        String senderName=new String(dataNodeInfo.getHostName())+new String(dataNodeInfo.getOutPortName());
        BlockManager blockManager=dataNodeInfo.getBlockManager(blockID);
        BlockSender blockSender=new BlockSender(senderName,receiverName,baudRate,blockManager.read());
    }
    public void interDataNodeWriteBlock(){

    }
    public void readBlock(){

    }

    public void writeReplica(){

    }

    @Override
    public void body(){


    }

}
