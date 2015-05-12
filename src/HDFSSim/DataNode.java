package hdfssim;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import json.DataNodeConfiguration;

/**
 * Created by Amemiya on 4/25/15.
 */
public class DataNode extends Sim_entity {
    private String ipAddr;
    private long capacity,remaining;
    private DataNodeInfo dataNodeInfo;
 //   private BlockReceiver blockReceiver;
    private Sim_port in,out;
    private
    DataNode(String ipAddr,long capacity,long remaining) throws Exception {
        super(ipAddr);
        dataNodeInfo=new DataNodeInfo(ipAddr,capacity,remaining,ipAddr+"in",ipAddr+"out");
        this.capacity=capacity;
        this.remaining=remaining;
        in=new Sim_port(ipAddr+"in");
        out=new Sim_port(ipAddr+"out");
        add_port(in);
        add_port(out);
    }
    DataNode(DataNodeConfiguration dataNodeConfiguration) throws Exception {
        super(dataNodeConfiguration.getIpAddr());
        DataNodeConfiguration df=dataNodeConfiguration;
        this.remaining=remaining;
        this.capacity=capacity;
        dataNodeInfo=new DataNodeInfo(df.getIpAddr(),df.getCapacity(),df.getRemaining(),ipAddr+"in",ipAddr+"out");
        in=new Sim_port(ipAddr+"out");
        out=new Sim_port(ipAddr+"in");
        add_port(in);
        add_port(out);
    }
    public void interDataNodeReadBlock(String receiverName, long blockID,double baudRate) throws Exception {

    }
    public void interDataNodeWriteBlock(){

    }
    public void readLocalBlock(){

    }

    public void writeReplica(){

    }

    @Override
    public void body(){

    }
}
