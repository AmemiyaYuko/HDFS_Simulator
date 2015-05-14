package test;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import hdfssim.Block;
import hdfssim.HDFSSimTags;
import hdfssim.ReadReplicaRequest;
import hdfssim.WriteReplicaRequest;

import java.util.ArrayList;

/**
 * Created by Amemiya on 5/14/15.
 */
public class TestEntity1 extends Sim_entity {
    Sim_port port=new Sim_port("port");
    TestEntity1(){
        super("a");
        add_port(port);
        Sim_system.link_ports("a", "port", "192.168.1.1", "port");
    }
    public void link(){

    }
    @Override
    public void body(){
        WriteReplicaRequest request=new WriteReplicaRequest(new Block(new Long(25253245),1000000,new ArrayList<String>()),1234);
        WriteReplicaRequest request1=new WriteReplicaRequest(new Block(new Long(123456),2100424,new ArrayList<String>()),1234);
        ReadReplicaRequest request2=new ReadReplicaRequest(new Long(123456),1234,0);
        sim_schedule("port", 0.0, HDFSSimTags.WRITE_REPLICA,request);
        sim_schedule("port", 10.0, HDFSSimTags.WRITE_REPLICA,request1);
        sim_schedule("port", 15.0, HDFSSimTags.READ_REPLICA,request2);
        sim_schedule("port", 120.0, HDFSSimTags.WRITE_REPLICA,request);
        sim_schedule("port", 1050.0, HDFSSimTags.WRITE_REPLICA,request);
        sim_schedule("port", 10566.0, HDFSSimTags.WRITE_REPLICA,request);
    }

}
