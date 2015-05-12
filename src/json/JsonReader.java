package json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Amemiya on 5/12/15.
 */
public class JsonReader {
    private ArrayList<NodeConfig> nodeConfigs=new ArrayList<NodeConfig>();
    private ArrayList<String> keywords=new ArrayList<String>(){{
        add("IP Address");
        add("Baud Rate");//bit per sec
        add("Capacity");//MB
        add("Read Speed");//MB per sec
        add("Write Speed");//MB per sec
        add("Seek Time");//sec
    }};
    public JsonReader(String fileName){
        String jsonString=new String(FileToString(fileName));
        JSONObject jsn=new JSONObject(jsonString);
        Iterator i=jsn.keys();
        while (i.hasNext()){
            //processing single machine config
            NodeConfig singleNodeConfig=new NodeConfig();
            String ipAddr=(String)i.next();
            JSONObject nodeObj=(JSONObject)jsn.get(ipAddr);
            //read ip and baud rate
            singleNodeConfig.setIpAddr(ipAddr);
            singleNodeConfig.setBaudRate((Double) nodeObj.get("Baud Rate"));
            //read disk config
            JSONObject diskArray=(JSONObject)nodeObj.get("Disk");
            Iterator j=diskArray.keys();
            while(j.hasNext()){
                //read single disk
                String diskID=(String)j.next();
                DiskConfig diskConfig=new DiskConfig(diskID);
                JSONObject diskObj=(JSONObject)diskArray.get(diskID);
                diskConfig.setCapacity((Double)diskObj.get("Capacity"));
                diskConfig.setSeekTime((Double) diskObj.get("Seek Time"));
                diskConfig.setSpeed((Double) diskObj.get("Read Speed"), (Double) diskObj.get("Write Speed"));
                singleNodeConfig.addDiskConfig(diskConfig);
            }
            nodeConfigs.add(nodeConfigs.size(),singleNodeConfig);
        }
    }
    private String FileToString(String fileName){
        File file = new File(fileName);
        System.out.print(file.exists());
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
        System.out.print(buffer.toString());
        return buffer.toString();
    }

    public NodeConfig getConfig(int i){
        return nodeConfigs.get(i);
    }
    public int numOfMachines(){
        return nodeConfigs.size();
    }
}
