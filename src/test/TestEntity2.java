package test;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import hdfssim.HDFSSimPredicate;
import hdfssim.HDFSSimTags;

/**
 * Created by Amemiya on 5/14/15.
 */
public class TestEntity2 extends Sim_entity {
    Sim_port port=new Sim_port("port");
    TestEntity2(){
        super("b");
        add_port(port);
    }
    @Override
    public void body(){
        while (Sim_system.running()){
            sim_schedule(port, 0, HDFSSimTags.WRITE_BLOCK);

            Sim_event e=new Sim_event();
            System.out.println(Sim_system.clock());
            sim_wait_for(new HDFSSimPredicate(HDFSSimTags.WRITE_BLOCK_FIN), e);
            System.out.println(Sim_system.clock());
            while(true);
        }
    }
}
