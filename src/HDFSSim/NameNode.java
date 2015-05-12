package hdfssim;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;

import java.util.ArrayList;

/**
 * Created by AmemiyaYuko on 2015/4/28.
 */
public class NameNode extends Sim_entity{
    ArrayList<DataNodeInfo> dataNodeInfos=new ArrayList<DataNodeInfo>();
    ArrayList<DataNode> dataNodes=new ArrayList<DataNode>();
    BlocksCollection blocksCollection=new BlocksCollection();
    public NameNode(String name) {
        super(name);
    }

    public void writeNewBlock(Long size){
        long blockID=blocksCollection.requestNewID();
        for (int replicas=0;replicas<3;replicas++){
            int target=getRandomNum(dataNodeInfos.size());
            while (this.getDataNodeInfo(target).getCapacity()>size)
                target=getRandomNum(dataNodeInfos.size());
            dataNodes.get(target).writeReplica();
        }
    }
    private int getRandomNum(int range){
        return (int) (Math.random() * range);
    }
    public DataNode getDataNode(int index){
        return dataNodes.get(index);
    }
    public DataNodeInfo getDataNodeInfo(int index){
        return dataNodeInfos.get(index);
    }
    @Override
    public void body(){
        int entityID;
        Sim_event ev=new Sim_event();
        for(sim_get_next(ev);ev.get_tag()!= HDFSSimTags.END_OF_SIMULATION;sim_get_next(ev)){
            if (ev.get_tag()==HDFSSimTags.WRITE_NEW_BLOCK){
                this.writeNewBlock((Long)ev.get_data());
            }
        }
    }

}
