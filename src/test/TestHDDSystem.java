package test;

import core.HDDID;
import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_system;
import hdfssim.*;
import json.DataNodeConfiguration;
import json.JsonReader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Amemiya on 5/12/15.
 */
public class TestHDDSystem extends Sim_entity{
    TestHDDSystem(){
        super("test");
    }
    public void body(){
        sim_schedule("192.168.1.1",0.0, HDFSSimTags.WRITE_BLOCK);
    }
    public static void main(String[] args){
        Sim_system.initialise();
        JsonReader jr=new JsonReader("/Users/Amemiya/Works/Code/HDFS_Simulator/machines.json","");
        ArrayList<DataNodeConfiguration> dc=new ArrayList<DataNodeConfiguration>();
        dc.add(new DataNodeConfiguration(jr.getConfig(0)));
        dc.add(new DataNodeConfiguration(jr.getConfig(1)));
        DataNode dn=new DataNode(dc.get(0));
        TestEntity1 t=new TestEntity1();
        Sim_system.run();

    }
}
