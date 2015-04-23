/**
 * Created by Amemiya on 4/23/15.
 */
package hdfssim;
import eduni.simjava.Sim_predicate;
import eduni.simjava.Sim_type_p;
import gridsim.GridSimTags;

public enum SimTag {
    START_SIMULATION,
    END_OF_SIMULATION,
    BLOCK_WRITE_REQUEST,
    BLOCK_READ_REQUEST,
    BLOCK_DELETE_REQUEST,
    WRITE_COMPLETE,
    READ_COMPLETE,
    DELETE_COMPLETE,
    HEARTBEAT,
    HEARTBEAT_REQUEST,
    HEARTBEAT_RESPONSE,
    BLOCK_REPORT_REQUEST,
    BLOCK_REPORT,
    SEND_REPLICATION_INTERDATANODE,
    REMOVE_LOCAL_BLOCK,
    SHUTDOWN_DATANODE,
    REGISTER_DATANODE;
   // public static final END_OF_SIMULATION=-1;
    public static SimTag get(int i){
        SimTag[] tags=SimTag.values();
        return SimTag.values()[i];
    }
    public static String toString(int i){
        SimTag tag=get(i);
        return tag.name();
    }
}
