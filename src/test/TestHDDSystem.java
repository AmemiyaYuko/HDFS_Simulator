package test;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_system;
import hdfssim.NameNode;

/**
 * Created by Amemiya on 5/12/15.
 */
public class TestHDDSystem extends Sim_entity {
    TestHDDSystem() {
        super("test");
    }


    public static void main(String[] args) {
        Sim_system.initialise();
        NameNode nm = new NameNode("/Users/Amemiya/Works/Code/HDFS_Simulator/machines.json");
        TestEntity1 t = new TestEntity1();
        Sim_system.run();

    }
}
