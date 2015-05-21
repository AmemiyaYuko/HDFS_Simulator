package hdfssim;

import core.HDDID;
import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import json.DataNodeConfiguration;
import logger.Logger;

import java.util.ArrayList;

/**
 * Created by Amemiya on 4/25/15.
 */
public class DataNode extends Sim_entity {
    private String ipAddr;
    private double capacity, remaining;
    private ArrayList<Sim_port> toHDD = new ArrayList<Sim_port>();
    private HDDSystem hddSystem;
    private double baudRate;
    private Sim_port portName = new Sim_port("port");

    public DataNode(DataNodeConfiguration dnConfig) {
        super(dnConfig.getIpAddr());
        this.ipAddr = dnConfig.getIpAddr();
        this.baudRate = dnConfig.getBaudRate();
        hddSystem = new HDDSystem(dnConfig);
        this.capacity = hddSystem.getCapacity();
        this.remaining = this.capacity;
        ArrayList<HDDID> hddids = hddSystem.getDisksID();
        for (int i = 0; i < hddids.size(); i++) {
            Sim_port port = new Sim_port(hddids.get(i).toString());
            toHDD.add(port);
            add_port(port);
            Sim_system.link_ports(this.ipAddr, hddids.get(i).toString(), hddids.get(i).toString(), "port");
        }
        add_port(portName);

    }

    public void insertReplica(WriteReplicaRequest request) {
        Block block = (Block) request;
        //System.out.print(block.getBlockID());
        HDDID hddid = hddSystem.selectIdleHDD(request.getSize());
        Sim_event e = new Sim_event();
        //System.out.println(Sim_system.clock());
        sim_schedule(hddid.toString(), 0.0, HDFSSimTags.WRITE_REPLICA, request);
        sim_wait_for(new HDFSSimPredicate(HDFSSimTags.WRITE_REPLICA_FIN), e);
        Logger.newEvent(request.getTrackID(), "Finished writing block" + request.getBlockID() + " to HDD " + hddid.toString(), Sim_system.clock());
        hddSystem.insertReplica(hddid, block);
        //System.out.println("end: " + Sim_system.clock());
    }

    public void readReplica(ReadReplicaRequest request) {
        long blockID = request.getBlockID();
        HDDID hddid = hddSystem.getHDDID(blockID);
        Sim_event e = new Sim_event();
        System.out.println(Sim_system.clock());
        sim_schedule(hddid.toString(), 0.0, HDFSSimTags.READ_REPLICA, request);
        sim_wait_for(new HDFSSimPredicate(HDFSSimTags.READ_REPLICA_FIN), e);
        Logger.newEvent(request.getTrackID(), "Finished reading block " + request.getBlockID() + " from " + hddid.toString(), Sim_system.clock());
        //System.out.println("end: " + Sim_system.clock());
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getRemaining() {
        return remaining;
    }

    public double getBaudRate() {
        return baudRate;
    }

    public boolean available(double size) {
        for (int i = 0; i < hddSystem.getDisksID().size(); i++) {
            if (hddSystem.getCapacities().get(i).doubleValue() > size) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void body() {
        while (Sim_system.running()) {
            Sim_event e = new Sim_event();
            sim_get_next(e);
            if (e.get_tag() == HDFSSimTags.WRITE_REPLICA) {
                WriteReplicaRequest request = (WriteReplicaRequest) e.get_data();
                Logger.newEvent(request.getTrackID(), "DataNode " + this.ipAddr + " received.", Sim_system.clock());
                insertReplica(request);
            }
            if (e.get_tag() == HDFSSimTags.READ_REPLICA) {
                ReadReplicaRequest request = (ReadReplicaRequest) e.get_data();
                Logger.newEvent(request.getTrackID(), "DataNode " + this.ipAddr + " received.", Sim_system.clock());
                readReplica(request);
            }
        }
    }

}
