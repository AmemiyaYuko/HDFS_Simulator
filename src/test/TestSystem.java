package test;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_system;
import hdfssim.NameNode;
import hdfssim.Simulator;
import logger.Logger;
import logger.LoggerEntity;

/**
 * Created by Amemiya on 5/12/15.
 */
public class TestSystem extends Sim_entity {
    TestSystem() {
        super("test");
    }

    public static void main(String[] args) {
        Sim_system.initialise();
        Logger.clean();
        NameNode nm = new NameNode("/Users/Amemiya/Works/Code/HDFS_Simulator/machines.json");
        Simulator sim = new Simulator();
        LoggerEntity logger = new LoggerEntity();
        Sim_system.run();

    }
}
