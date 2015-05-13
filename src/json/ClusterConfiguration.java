package json;

import java.util.ArrayList;
/**
 * Created by Amemiya on 4/25/15.
 */
public class ClusterConfiguration {
    private ArrayList<DataNodeConfiguration> dataNodeConfigurations;
    public ClusterConfiguration(String dataNodeConfigFileName){
        JsonReader jr=new JsonReader(dataNodeConfigFileName,"");

    }
    public ArrayList<DataNodeConfiguration> getDataNodeConfigurations() {
        return dataNodeConfigurations;
    }

    public DataNodeConfiguration getDataNodeConfiguration(int i){
        return dataNodeConfigurations.get(i);
    }

    public void setDataNodeConfigurations(ArrayList<DataNodeConfiguration> dataNodeConfigurations) {
        this.dataNodeConfigurations = dataNodeConfigurations;
    }
    public int numberOfDataNodes(){
        return dataNodeConfigurations.size();
    }

}
