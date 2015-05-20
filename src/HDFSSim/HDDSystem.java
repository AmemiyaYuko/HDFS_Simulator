package hdfssim;

import core.HDD;
import core.HDDID;
import json.DataNodeConfiguration;
import json.DiskConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Amemiya on 5/9/15.
 */
public class HDDSystem {
    private ArrayList<HDDID> disksID = new ArrayList<HDDID>();
    private HashMap<HDDID, HDD> disksMap = new HashMap<HDDID, HDD>();
    private HashMap<Long, HDDID> blocksMap = new HashMap<Long, HDDID>();
    //              blockID , HDDID
    public HDDSystem(DataNodeConfiguration dnConfig) {
        System.out.println("Initializing hard disk drivers for " + dnConfig.getIpAddr());
        ArrayList<DiskConfig> diskConfigs = dnConfig.getDiskConfigs();
        int sizeOfDiskConfigs = diskConfigs.size();
        for (int i = 0; i < sizeOfDiskConfigs; i++) {
            DiskConfig dc = diskConfigs.get(i);
            HDDID hddid = new HDDID(dnConfig.getIpAddr(), dc.getId());
            HDD hdd = new HDD(hddid, dc.getCapacity(), dc.getReadSpeed(), dc.getWriteSpeed(), dc.getSeekTime());
            disksID.add(hddid);
            disksMap.put(hddid, hdd);
        }
        System.out.println("Hard Disk Drivers initialized!");
    }

    public HDDID selectIdleHDD(double size) {
        HDDID idle = null;
        Iterator it = disksMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) it.next();
            HDD val = (HDD) entry.getValue();
            HDDID key = (HDDID) entry.getKey();
            //System.out.print("| "+Sim_system.clock()+" : "+val.getHddid().toString()+" : "+val.sim_waiting()+" |\n");
            if (val.getCapacity()>=size) {
                idle = key;
            }
        }
        return idle;
    }

    public void insertReplica(HDDID hddid, Block block) {
        blocksMap.put(new Long(block.getBlockID()), hddid);
        disksMap.get(hddid).expend(block.getSize());
    }

    public HDDID getHDDID(long blockID) {
        return blocksMap.get(new Long(blockID));
    }

    public boolean containBlock(Block key) {
        return blocksMap.containsKey(key);
    }

    public double getCapacity() {
        double cap = 0;
        Iterator it = disksMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) it.next();
            HDD val = (HDD) entry.getValue();
            cap += val.getCapacity();
        }
        return cap;
    }

    public ArrayList<Double> getCapacities(){
        ArrayList<Double> cap = new ArrayList<Double>();
        Iterator it = disksMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) it.next();
            HDD val = (HDD) entry.getValue();
            cap.add(val.getCapacity());
        }
        return cap;
    }

    public double getRemaining() {
        double rem = 0;
        Iterator it = disksMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) it.next();
            HDD val = (HDD) entry.getValue();
            rem += val.getRemaining();
        }
        return rem;
    }

    public ArrayList<HDDID> getDisksID() {
        return disksID;
    }

    @Override
    public String toString() {
        String r = new String();
        Iterator it = disksMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) it.next();
            HDD val = (HDD) entry.getValue();
            r = r + val.toString();
        }
        it = blocksMap.entrySet().iterator();
        r = r + "Hosted blocks:";
        while (it.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) it.next();
            Block key = (Block) entry.getKey();
            r = r + key.toString() + "\n";
        }
        return r;
    }

}
