package core;

import hdfssim.Node;


import java.util.LinkedList;



/**
 * Created by Amemiya on 5/9/15.
 */
public class HDD extends Node{
    //Long---The end time of each task;
    //HDDIOTask---Task information;
    LinkedList<HDDIOTask> taskQueue=new LinkedList<HDDIOTask>();
    long lastTick;
    HDDIOTask currentTask;
    public HDD(String ipAddr, String hostName) {
        super(ipAddr, hostName);
    }
    public boolean isIdle(){
        return taskQueue.isEmpty();
    }
    public long ticksToIdle(long currentTick){
        return lastTick-currentTick;
    }
    public void work(long currentTick) {
        if (currentTask.getEndTime() == currentTick) {
            taskQueue.pollFirst();
            currentTask = taskQueue.getFirst();
        }
    }
    public void addTask(HDDIOTask task){
        taskQueue.addLast(task);
    }
}
