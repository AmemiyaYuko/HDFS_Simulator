package hdfssim;

import core.HDD;
import eduni.simjava.Sim_entity;
import json.ClusterConfiguration;

import java.util.ArrayList;

/**
 * Created by Amemiya on 5/9/15.
 */
public class HDDSystem extends Sim_entity{
    private static ArrayList<HDD> disks=new ArrayList<HDD>();
    HDDSystem(int numberOfDisk){
        super("HDDSystem");
        System.out.println("Initializing hard disk drivers...");
        for (int i=0;i<numberOfDisk;i++){
            String ipAddr=ClusterConfiguration.getDataNodeConfiguration(i).getIpAddr();
            String hostName=ClusterConfiguration.getDataNodeConfiguration(i).getHostName();
            disks.add(new HDD(ipAddr,hostName));
        }
        System.out.println("Hard Disk Drivers initialized");
    }


}
