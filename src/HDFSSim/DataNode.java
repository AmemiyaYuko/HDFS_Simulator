package HDFSSim;

import json.ClusterConfiguration;
import json.DataNodeConfiguration;

/**
 * Created by Amemiya on 4/25/15.
 */
public class DataNode {
    DataNodeInfo dataNodeInfo;
    BlockReceiver blockReceiver;
    int dataNodeID;

    DataNode(int DataNodeID,String ipAddr,String hostName,String inPortName,String outPortName,long capacity,long remaining) throws Exception {
        this.dataNodeID=DataNodeID;
        dataNodeInfo=new DataNodeInfo(ipAddr,hostName,inPortName,outPortName,capacity,remaining);
        blockReceiver=new BlockReceiver(new String(hostName+inPortName), ClusterConfiguration.getDataNodeConfiguration(dataNodeID).getBaudRate());
    }
    DataNode(DataNodeConfiguration dataNodeConfiguration){
        this.dataNodeID=dataNodeConfiguration.getDataNodeID();

    }

}
