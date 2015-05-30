package hdfssim;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import logger.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Amemiya on 5/9/15.
 */
public class Simulator extends Sim_entity {
    Sim_port port = new Sim_port("port");

    public Simulator() {
        super("simulator");
        add_port(port);
        Sim_system.link_ports("simulator", "port", "namenode", "port");
    }

    private void singleReadRequest(String fileName, double offset, int delay) {
        ReadFileRequest request = new ReadFileRequest(fileName, offset);
        System.out.print(request.toString());
        sim_schedule("port", delay, HDFSSimTags.READ_FILE, request);
    }

    private void singleWriteRequest(String fileName, double size, int delay) {
        WriteNewFileRequest request = new WriteNewFileRequest(fileName, size);
        System.out.print(request.toString());
        sim_schedule("port", delay, HDFSSimTags.WRITE_NEW_FILE, request);
    }

    public String FileToString(String fileName) {
        File file = new File(fileName);
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        //System.out.print(buffer.toString());
        return buffer.toString();
    }

    @Override
    public void body() {
        String jsonString = new String(FileToString("requestlist.json"));
        JSONArray jsn = new JSONArray(jsonString);
        int interval = 10;
        int delay = 0;
        Logger.setNumberOfRequests(jsn.length());
        for (int i = 0; i < jsn.length(); i++) {
            JSONObject jsnobj = jsn.getJSONObject(i);
            if (jsnobj.get("type").equals("read")) {
                singleReadRequest(jsnobj.getString("file name"), jsnobj.getDouble("offset"), jsnobj.getInt("delay") + delay);
            }
            if (jsnobj.get("type").equals("write")) {
                singleWriteRequest(jsnobj.getString("file name"), jsnobj.getDouble("size"), jsnobj.getInt("delay") + delay);
            }
            delay += interval;
        }
        System.out.println("Read request list successful!");

    }

}
