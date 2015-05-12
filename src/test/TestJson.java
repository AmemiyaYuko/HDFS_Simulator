package test;

import json.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Amemiya on 5/12/15.
 */
public class TestJson {

    public static void main(String[] args){
        JsonReader jr=new JsonReader("/Users/Amemiya/Works/Code/HDFS_Simulator/machines.json");
        NodeConfig nc=jr.getConfig(0);
        System.out.println('\n'+nc.getIpAddr());
        System.out.println(nc.getBaudRate());
        ArrayList<DiskConfig> dcs=nc.getDiskConfigs();
        for (int i=0;i<nc.numberOfDisks();i++){
            DiskConfig dc=dcs.get(i);
            System.out.println(dc.getId());
            System.out.println(dc.getParam("Capacity"));
            System.out.println(dc.getParam("Seek Time"));
            System.out.println(dc.getParam("Write Speed"));
            System.out.println(dc.getParam("Read Speed"));
        }
        nc=jr.getConfig(1);
        System.out.println('\n'+nc.getIpAddr());
        System.out.println(nc.getBaudRate());
        dcs=nc.getDiskConfigs();
        for (int i=0;i<nc.numberOfDisks();i++){
            DiskConfig dc=dcs.get(i);
            System.out.println(dc.getId());
            System.out.println(dc.getParam("Capacity"));
            System.out.println(dc.getParam("Seek Time"));
            System.out.println(dc.getParam("Write Speed"));
            System.out.println(dc.getParam("Read Speed"));
        }

        //System.out.print(jr.numOfMachines());
    }
}
