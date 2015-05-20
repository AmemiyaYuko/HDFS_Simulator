package hdfssim;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import json.ClusterConfiguration;
import logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by AmemiyaYuko on 2015/4/28.
 */
public class NameNode extends Sim_entity {
    private HashMap<Long, List> inode = new HashMap<Long, List>();
    //              block id, datanode ip addr list
    private HashMap<String, List> namespace = new HashMap<String, List>();
    //              file name, block id list
    private ArrayList<Sim_port> toDataNodes = new ArrayList<Sim_port>();
    private Sim_port toUser = new Sim_port("port");
    BlocksCollection blocksCollection = new BlocksCollection();
    private HashMap<String, DataNode> dataNodesMap = new HashMap<String, DataNode>();
    //              ip addr, datanode
    private static final double MAX_BYTES_PER_BLOCK=64*1024;

    public NameNode(String path) {
        super("namenode");
        ClusterConfiguration config = new ClusterConfiguration(path);
        for (int i = 0; i < config.getSize(); i++) {
            DataNode dn = new DataNode(config.getDataNodeConfiguration(i));
            dataNodesMap.put(new String(dn.getIpAddr()), dn);
            Sim_port port = new Sim_port(new String(dn.getIpAddr()));
            toDataNodes.add(port);
            add_port(port);
            Sim_system.link_ports("namenode", dn.getIpAddr(), dn.getIpAddr(), "port");
        }
        add_port(toUser);
    }

    private Block blockConstruction(double size){
        long id=blocksCollection.requestNewID();
        Block b=new Block(id,size);
        return b;
    }

    private ArrayList<Block> fileSplitter(double size){
        double remaining=size;
        ArrayList<Block> blocks=new ArrayList<Block>();
        while (remaining>MAX_BYTES_PER_BLOCK){
            remaining=remaining-MAX_BYTES_PER_BLOCK;
            Block b=blockConstruction(MAX_BYTES_PER_BLOCK);
            blocks.add(b);
        }
        if (remaining>0){
            Block b=blockConstruction(remaining);
            blocks.add(b);
        }
        return blocks;
    }
    private void writeBlock(Block block, int trackID){
        WriteReplicaRequest request=new WriteReplicaRequest(block,trackID);
        ArrayList<String> ipList=new ArrayList<String>();
        int remain=3;
        Set set = dataNodesMap.entrySet() ;
        java.util.Iterator it = dataNodesMap.entrySet().iterator();
        while ((it.hasNext())&&(remain>0)){
            java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
            DataNode dn=(DataNode)entry.getValue();
            if (dn.available(block.getSize())) {
                Logger.newEvent(trackID,"Send write block event to "+dn.getIpAddr(),Sim_system.clock());
                sim_schedule(dn.getIpAddr(), 0.0, HDFSSimTags.WRITE_REPLICA, request);
                ipList.add(dn.getIpAddr());
                remain--;
            }
        }
        inode.put(Long.valueOf(block.getBlockID()),ipList);
    }
    private void writeNewFile(String fileName,double size){
        ArrayList<Block> blocksList=fileSplitter(size);
        ArrayList<Long> blocksIDList=new ArrayList<Long>();
        int trackID=Logger.newTrack("Writing"+fileName,Sim_system.clock());
        Logger.newEvent(trackID,fileName+" was splitted into "+blocksList.size()+" blocks",Sim_system.clock());
        for (int i=0;i<blocksList.size();i++){
            blocksIDList.add(blocksList.get(i).getBlockID());
            writeBlock(blocksList.get(i), trackID);
        }
        namespace.put(fileName,blocksIDList);
    }

    @Override
    public void body() {
        while (Sim_system.running()) {
            Sim_event e = new Sim_event();
            sim_get_next(e);
            if (e.get_tag() == HDFSSimTags.WRITE_NEW_FILE) {
                WriteNewFileRequest request = (WriteNewFileRequest) e.get_data();
                writeNewFile(request.getFileName(), request.getSize());

            }
        }
    }
}
