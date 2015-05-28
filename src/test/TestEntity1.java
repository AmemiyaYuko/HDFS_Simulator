package test;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import hdfssim.HDFSSimTags;
import hdfssim.ReadFileRequest;
import hdfssim.WriteNewFileRequest;
import logger.Logger;

import java.io.IOException;

/**
 * Created by Amemiya on 5/14/15.
 */
public class TestEntity1 extends Sim_entity {
    Sim_port port = new Sim_port("port");

    TestEntity1() {
        super("test1");
        add_port(port);
        Sim_system.link_ports("test1", "port", "namenode", "port");
    }

    @Override
    public void body() {
        WriteNewFileRequest request1 = new WriteNewFileRequest("f1", 90400);
        sim_schedule("port", 0.0, HDFSSimTags.WRITE_NEW_FILE, request1);
        WriteNewFileRequest request2 = new WriteNewFileRequest("f2", 90020);
        sim_schedule("port", 10.0, HDFSSimTags.WRITE_NEW_FILE, request2);
        WriteNewFileRequest request3 = new WriteNewFileRequest("f3", 900240);
        sim_schedule("port", 15.0, HDFSSimTags.WRITE_NEW_FILE, request3);
        WriteNewFileRequest request4 = new WriteNewFileRequest("f4", 900240);
        sim_schedule("port", 20.0, HDFSSimTags.WRITE_NEW_FILE, request4);
        ReadFileRequest request5 = new ReadFileRequest("f3", 2100);
        sim_schedule("port", 5000.0, HDFSSimTags.READ_FILE, request5);
        ReadFileRequest request6 = new ReadFileRequest("f3", 200);
        sim_schedule("port", 5003.0, HDFSSimTags.READ_FILE, request6);
        ReadFileRequest request7 = new ReadFileRequest("f2", 200);
        sim_schedule("port", 5006.0, HDFSSimTags.READ_FILE, request7);
        sim_pause(100000000);
        try {
            Logger.output();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
