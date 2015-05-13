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
    private ArrayList<HDDID> disksID=new ArrayList<HDDID>();
    private HashMap<HDDID,HDD> disksMap=new HashMap<HDDID, HDD>();
    private HashMap<Block,HDDID> blocksMap=new HashMap<Block, HDDID>();
    public HDDSystem(DataNodeConfiguration dnConfig){
        System.out.println("Initializing hard disk drivers for "+dnConfig.getIpAddr());
        ArrayList<DiskConfig> diskConfigs=dnConfig.getDiskConfigs();
        int sizeOfDiskConfigs=diskConfigs.size();
        for(int i=0;i<sizeOfDiskConfigs;i++){
            DiskConfig dc=diskConfigs.get(i);
            HDDID hddid=new HDDID(dnConfig.getIpAddr(),dc.getId());
            HDD hdd=new HDD(hddid,dc.getCapacity(),dc.getReadSpeed(),dc.getWriteSpeed(),dc.getSeekTime());
            disksID.add(hddid);
            disksMap.put(hddid,hdd);
        }
        System.out.println("Hard Disk Drivers initialized!");
    }

    public HDDID selectIdleHDD(){
        HDDID idle=null;
        int waiting=0;
        Iterator it = disksMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) it.next();
            HDD val = (HDD)entry.getValue();
            HDDID key=(HDDID)entry.getKey();
            if (idle==null) {
                idle=key;
                waiting=val.sim_waiting();
            }
            if (val.sim_waiting()<waiting){
                idle=key;
                waiting=val.sim_waiting();
            }
        }
        return idle;
    }

    public void insertReplica(HDDID hddid,Block block){
        blocksMap.put(block, hddid);
        disksMap.get(hddid).expend(block.getSize());
    }

    public HDDID getHDDID(Block block){
        return blocksMap.get(block);
    }

    public boolean containBlock(Block key){
        return blocksMap.containsKey(key);
    }

    public double getCapacity(){
        double cap=0;
        Iterator it = disksMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) it.next();
            HDD val = (HDD)entry.getValue();
            cap+=val.getCapacity();
        }
        return cap;
    }

    public double getRemaining(){
        double rem=0;
        Iterator it = disksMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) it.next();
            HDD val = (HDD)entry.getValue();
            rem+=val.getRemaining();
        }
        return rem;
    }

    public ArrayList<HDDID> getDisksID() {
        return disksID;
    }

    @Override
    public String toString(){
        String r = new String();
        Iterator it = disksMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) it.next();
            HDD val = (HDD)entry.getValue();
            r=r+val.toString();
        }
        it = blocksMap.entrySet().iterator();
        r=r+"Hosted blocks:";
        while (it.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) it.next();
            Block key = (Block)entry.getKey();
            r=r+key.toString()+" : "+key.getSize()+"KB\n";
        }
        return r;
    }

}
