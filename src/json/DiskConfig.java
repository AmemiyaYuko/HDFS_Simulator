package json;

import java.util.HashMap;

/**
 * Created by Amemiya on 5/12/15.
 */
public class DiskConfig {
    private HashMap<String,Double> diskConfig=new HashMap<String, Double>();
    private String id;
    DiskConfig(String id){
        this.id=id;
    }
    public void setCapacity(Double capacity){
        diskConfig.put("Capacity", capacity);
    }
    public void setSpeed(Double readSpeed,Double writeSpeed){
        diskConfig.put("Read Speed",readSpeed);
        diskConfig.put("Write Speed",writeSpeed);
    }
    public void setSeekTime(Double seekTime){
        diskConfig.put("Seek Time",seekTime);
    }
    public double getParam(String key){
        return diskConfig.get(key).doubleValue();
    }

    public String getId() {
        return id;
    }
}
