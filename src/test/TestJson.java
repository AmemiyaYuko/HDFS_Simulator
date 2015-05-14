package test;

/**
 * Created by Amemiya on 5/12/15.
 * <p/>
 * public class TestJson {
 * <p/>
 * public static void main(String[] args){
 * JsonReader jr=new JsonReader("/Users/Amemiya/Works/Code/HDFS_Simulator/machines.json");
 * NodeConfig nc=jr.getConfig(0);
 * System.out.println('\n'+nc.getIpAddr());
 * System.out.println(nc.getBaudRate());
 * ArrayList<DiskConfig> dcs=nc.getDiskConfigs();
 * for (int i=0;i<nc.numberOfDisks();i++){
 * DiskConfig dc=dcs.get(i);
 * System.out.println(dc.getId());
 * System.out.println(dc.getParam("Capacity"));
 * System.out.println(dc.getParam("Seek Time"));
 * System.out.println(dc.getParam("Write Speed"));
 * System.out.println(dc.getParam("Read Speed"));
 * }
 * nc=jr.getConfig(1);
 * System.out.println('\n'+nc.getIpAddr());
 * System.out.println(nc.getBaudRate());
 * dcs=nc.getDiskConfigs();
 * for (int i=0;i<nc.numberOfDisks();i++){
 * DiskConfig dc=dcs.get(i);
 * System.out.println(dc.getId());
 * System.out.println(dc.getParam("Capacity"));
 * System.out.println(dc.getParam("Seek Time"));
 * System.out.println(dc.getParam("Write Speed"));
 * System.out.println(dc.getParam("Read Speed"));
 * }
 * <p/>
 * //System.out.print(jr.numOfMachines());
 * }
 * }
 */