package hdfssim;

import core.HDD;
import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_system;
import json.ClusterConfiguration;

import java.util.ArrayList;

/**
 * Created by Amemiya on 5/9/15.
 */
public class HDDSystem extends Sim_entity{
    private static ArrayList<HDD> disks=new ArrayList<HDD>();
    private long lastDiskID=0;
    HDDSystem(int numberOfDisk){
        super("HDDSystem");
        System.out.println("Initializing hard disk drivers...");
        for (int i=0;i<numberOfDisk;i++){
            String ipAddr=ClusterConfiguration.getDataNodeConfiguration(i).getIpAddr();
            //disks.add(new HDD(ipAddr));
        }
        System.out.println("Hard Disk Drivers initialized");
    }
    @Override
    public void body(){
        while(Sim_system.running()){
            Sim_event e=new Sim_event();
            sim_get_next(e);

        }
    }


}
