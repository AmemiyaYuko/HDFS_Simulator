package hdfssim;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import json.ClusterConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by AmemiyaYuko on 2015/4/28.
 */
public class NameNode extends Sim_entity {
    private HashMap<Long, List> inode = new HashMap<Long, List>();
    private HashMap<String, List> namespace = new HashMap<String, List>();
    private ArrayList<Sim_port> toDataNodes = new ArrayList<Sim_port>();
    private Sim_port toUser = new Sim_port("port");
    BlocksCollection blocksCollection = new BlocksCollection();
    private HashMap<String, DataNode> dataNodesMap = new HashMap<String, DataNode>();

    NameNode(String path) {
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


}
