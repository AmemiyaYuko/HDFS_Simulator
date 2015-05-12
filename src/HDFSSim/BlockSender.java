package hdfssim;
/**
import gridsim.GridSim;
import gridsim.GridSimTags;


 * Created by Amemiya on 4/25/15.

public class BlockSender extends GridSim{
    private String entityName;
    private String receiverName;
    private boolean hasData;
    private byte[] gridlet;
    BlockSender(String entityName,String receiverName,double baud_rate,byte[] gridlet) throws Exception{
        super(entityName,baud_rate);
        this.receiverName=new String(receiverName);
        this.gridlet=gridlet;
    }

    @Override
    public void body() {
        super.send(receiverName, GridSimTags.SCHEDULE_NOW,GridSimTags.GRIDLET_SUBMIT, gridlet);
    }

}
 */