package test;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import hdfssim.*;
import logger.Logger;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Amemiya on 5/14/15.
 */
public class TestEntity1 extends Sim_entity {
    Sim_port port = new Sim_port("port");

    TestEntity1() {
        super("a");
        add_port(port);
        Sim_system.link_ports("a", "port", "namenode", "port");
    }

    public void link() {

    }

    @Override
    public void body() {
        WriteNewFileRequest request=new WriteNewFileRequest("noob",900240);
        sim_schedule("port",0.0,HDFSSimTags.WRITE_NEW_FILE,request);

        sim_pause(100000000);
        try {
            Logger.output();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
