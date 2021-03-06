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
    //              ip addr, datanode
    private static final double MAX_BYTES_PER_BLOCK = 64 * 1024;
    BlocksCollection blocksCollection = new BlocksCollection();
    private HashMap<Long, List> inode = new HashMap<Long, List>();
    //              block id, datanode ip addr list
    private HashMap<String, List> namespace = new HashMap<String, List>();
    //              file name, block id list
    private ArrayList<Sim_port> toDataNodes = new ArrayList<Sim_port>();
    private Sim_port toUser = new Sim_port("port");
    private HashMap<String, DataNode> dataNodesMap = new HashMap<String, DataNode>();

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

    private Block blockConstruction(double size) {
        long id = blocksCollection.requestNewID();
        Block b = new Block(id, size);
        return b;
    }

    private ArrayList<Block> fileSplitter(double size) {
        double remaining = size;
        ArrayList<Block> blocks = new ArrayList<Block>();
        while (remaining > MAX_BYTES_PER_BLOCK) {
            remaining = remaining - MAX_BYTES_PER_BLOCK;
            Block b = blockConstruction(MAX_BYTES_PER_BLOCK);
            blocks.add(b);
        }
        if (remaining > 0) {
            Block b = blockConstruction(remaining);
            blocks.add(b);
        }
        return blocks;
    }

    private void writeBlock(Block block, int trackID, HashMap<String, Integer> futureEvent) {
        WriteReplicaRequest request = new WriteReplicaRequest(block, trackID);
        ArrayList<String> ipList = new ArrayList<String>();
        int remain = 3;

        for (int i = 0; i < 3; i++) {
            Set set = dataNodesMap.entrySet();
            java.util.Iterator it = dataNodesMap.entrySet().iterator();
            int waiting = 999999;
            String ipAddr = new String();
            while (it.hasNext()) {
                java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
                DataNode dn = (DataNode) entry.getValue();
                Integer dnWaiting = dn.sim_waiting() + dn.getRunning();
                if (futureEvent.containsKey(dn.getIpAddr())) {
                    dnWaiting = dnWaiting + futureEvent.get(dn.getIpAddr());
                }
                if (dn.available(block.getSize()) && (dnWaiting < waiting)) {
                    ipAddr = dn.getIpAddr();
                    waiting = dnWaiting;
                }
            }
            Logger.newEvent(trackID, "Sending write block " + block.getBlockID() + " event to " + ipAddr, Sim_system.clock());
            sim_schedule(ipAddr, 0.0, HDFSSimTags.WRITE_REPLICA, request);
            ipList.add(ipAddr);
            futureEvent.put(ipAddr, waiting + 1);
        }
        inode.put(Long.valueOf(block.getBlockID()), ipList);
    }

    private void writeNewFile(String fileName, double size) {
        HashMap<String, Integer> futureEvent = new HashMap<String, Integer>(0);
        ArrayList<Block> blocksList = fileSplitter(size);
        ArrayList<Long> blocksIDList = new ArrayList<Long>();
        int trackID = Logger.newTrack("Writing" + fileName, Sim_system.clock());
        Logger.newEvent(trackID, fileName + " was splitted into " + blocksList.size() + " blocks", Sim_system.clock());
        for (int i = 0; i < blocksList.size(); i++) {
            blocksIDList.add(blocksList.get(i).getBlockID());
            writeBlock(blocksList.get(i), trackID, futureEvent);
        }
        namespace.put(fileName, blocksIDList);
    }

    private void readFile(String fileName, double offset) {
        List<Long> blockIDList = namespace.get(fileName);
        double iOffset = offset;
        int trackID = Logger.newTrack("Read" + fileName, Sim_system.clock());
        for (int i = 0; i < blockIDList.size(); i++) {
            if (iOffset < MAX_BYTES_PER_BLOCK) {
                Long blockID = blockIDList.get(i);
                List<String> ipList = inode.get(blockID);
                String ipAddr = ipList.get(0);
                DataNode dn = dataNodesMap.get(ipAddr);
                int waiting = dn.sim_waiting() + dn.getRunning();
                for (int j = 1; j < ipList.size(); j++) {
                    DataNode tmp = dataNodesMap.get(ipList.get(j));
                    if ((tmp.sim_waiting() + tmp.getRunning()) < waiting) {
                        ipAddr = ipList.get(j);
                        dn = tmp;
                        waiting = dn.sim_waiting() + dn.getRunning();
                    }
                }
                Logger.newEvent(trackID, "Sending block " + blockID + " request to " + ipAddr, Sim_system.clock());
                ReadReplicaRequest request = new ReadReplicaRequest(blockID.longValue(), trackID, iOffset);
                if (iOffset != 0) iOffset = 0;
                sim_schedule(ipAddr, 0.0, HDFSSimTags.READ_REPLICA, request);
            } else {
                iOffset = iOffset - MAX_BYTES_PER_BLOCK;
            }
        }

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
            if (e.get_tag() == HDFSSimTags.READ_FILE) {
                ReadFileRequest request = (ReadFileRequest) e.get_data();
                if (namespace.containsKey(request.getFileName())) {
                    readFile(request.getFileName(), request.getOffset());
                } else {
                    System.out.println("File " + request.getFileName() + " does not exist!");
                }
            }
        }
    }
}
