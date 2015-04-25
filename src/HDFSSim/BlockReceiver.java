package HDFSSim;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import gridsim.GridSim;
import gridsim.GridSimTags;

/**
 * Created by Amemiya on 4/25/15.
 */
public class BlockReceiver extends GridSim {
    private String entityName;
    BlockReceiver(String entityName,double baud_rate) throws Exception{
        super(entityName,baud_rate);
    }

    @Override
    public void body() {
        Sim_event ev=new Sim_event();
        for (sim_get_next(ev);ev.get_tag()!= GridSimTags.END_OF_SIMULATION;sim_get_next(ev)){
            //Add gridlet message there
            super.send(ev.get_src(), GridSimTags.SCHEDULE_NOW,GridSimTags.END_OF_SIMULATION);
        }
    }
}
