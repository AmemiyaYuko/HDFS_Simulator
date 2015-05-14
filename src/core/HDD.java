package core;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import hdfssim.HDFSSimTags;
import hdfssim.ReadReplicaRequest;
import hdfssim.WriteReplicaRequest;

import java.util.LinkedList;

/**
 *
 */
public class HDD extends Sim_entity{
    //HDDIOTask---Task information;
    private HDDID hddid;
    private double capacity;
    private double remaining,readSpeed,writeSpeed,seekTime;
    private Sim_port port=new Sim_port("port");
    public HDD(HDDID hddid,double capacity,double readSpeed,double writeSpeed,double seekTime) {
        super(hddid.toString());
        this.hddid=hddid;
        this.capacity=capacity;
        this.readSpeed=readSpeed;
        this.writeSpeed=writeSpeed;
        this.seekTime=seekTime;
        this.remaining=capacity;
        add_port(port);
    }

    public HDDID getHddid() {
        return hddid;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getRemaining() {
        return remaining;
    }

    public double getReadSpeed() {
        return readSpeed;
    }

    public double getWriteSpeed() {
        return writeSpeed;
    }

    public double getSeekTime() {
        return seekTime;
    }

    public void expend(double size){
        this.remaining-=size;
    }
    @Override
    public String toString(){
        String r;
        r=hddid.toString()+" stat:";
        r=r+"\nCapacity: "+this.capacity;
        r=r+"\nRead Speed"+this.readSpeed;
        r=r+"\nWrite Speed"+this.writeSpeed;
        r=r+"\nSeek Time"+this.seekTime;
        r=r+"\nRemaining"+this.remaining;
        r=r+"\n-----\n";
        return r;
    }
    @Override
    public void body(){
        while(Sim_system.running()){
            Sim_event e=new Sim_event();
            sim_get_next(e);
            if (e.get_tag()== HDFSSimTags.WRITE_REPLICA){
                //System.out.println(this.hddid.toString() + " " + Sim_system.clock() + "received");
                WriteReplicaRequest request=(WriteReplicaRequest)e.get_data();
                sim_schedule(port, request.consumption(this.getWriteSpeed()),HDFSSimTags.WRITE_REPLICA_FIN);
                //System.out.println(this.hddid.toString() + " " + Sim_system.clock() + "finished");
            }
            if (e.get_tag()==HDFSSimTags.READ_REPLICA){
                //System.out.println(this.hddid.toString() + " " + Sim_system.clock() + "received");
                ReadReplicaRequest request=(ReadReplicaRequest)e.get_data();
                sim_schedule(port, request.consumption(this.getReadSpeed())+this.seekTime,HDFSSimTags.READ_REPLICA_FIN);
                //System.out.println(this.hddid.toString() + " " + Sim_system.clock() + "finished");
            }
        }
    }
}
