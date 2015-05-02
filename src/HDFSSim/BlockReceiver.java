package HDFSSim;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import gridsim.GridSim;
import gridsim.GridSimTags;

import javax.xml.crypto.Data;

/**
 * Created by Amemiya on 4/25/15.
 */
public class BlockReceiver extends GridSim {
    private String entityName;
    private String nodeName;
    private DataNode dataNode;
    private byte[] data;
    private boolean received=false;
    BlockReceiver(String entityName,double baud_rate,DataNode dataNode) throws Exception{
        super(new String(entityName),baud_rate);
        this.nodeName=nodeName;
    }

    @Override
    public void body() {
        Sim_event ev=new Sim_event();
        for (sim_get_next(ev);ev.get_tag()!= GridSimTags.END_OF_SIMULATION;sim_get_next(ev)){
            if (ev.get_tag()==HDFSSimTags.READ_REQUEST){
                this.received=true;
            }
            //Add gridlet message there
            super.send(ev.get_src(), GridSimTags.SCHEDULE_NOW,GridSimTags.END_OF_SIMULATION);
        }
    }
    public boolean hasReceived(){
        return received;
    }
}
