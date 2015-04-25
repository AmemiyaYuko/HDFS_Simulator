package HDFSSim;

import gridsim.GridSim;
import gridsim.GridSimTags;

import java.util.Objects;

/**
 * Created by Amemiya on 4/25/15.
 */
public class BlockSender extends GridSim{
    private String entityName;
    private String receiverName;
    private boolean hasData;
    private Objects gridlet;
    BlockSender(String entityName,String receiverName,double baud_rate,Objects gridlet) throws Exception{
        super(entityName,baud_rate);
        this.receiverName=new String(receiverName);
        this.gridlet=gridlet;
    }

    @Override
    public void body() {
        super.send(receiverName, GridSimTags.SCHEDULE_NOW,GridSimTags.GRIDLET_SUBMIT, gridlet);
    }

}
