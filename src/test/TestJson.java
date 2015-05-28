package test;


import json.DiskConfig;
import json.JsonReader;
import json.NodeConfig;

import java.util.ArrayList;

public class TestJson {
    public void test(String[] args) {
        JsonReader jr = new JsonReader("/Users/Amemiya/Works/Code/HDFS_Simulator/machines.json");
        NodeConfig nc = jr.getConfig(0);
        System.out.println('\n' + nc.getIpAddr());
        System.out.println(nc.getBaudRate());
        ArrayList<DiskConfig> dcs = nc.getDiskConfigs();

        for (int i = 0; i < nc.numberOfDisks(); i++) {
            DiskConfig dc = dcs.get(i);
            System.out.println(dc.getId());
        }
        nc = jr.getConfig(1);
        System.out.println('\n' + nc.getIpAddr());
        System.out.println(nc.getBaudRate());
        dcs = nc.getDiskConfigs();
        for (int i = 0; i < nc.numberOfDisks(); i++) {
            DiskConfig dc = dcs.get(i);
        }
    }
}
