package core;

import java.util.LinkedList;



/**
 * Created by Amemiya on 5/9/15.
 */
public class HDD {
    //Long---The end time of each task;
    //HDDIOTask---Task information;
    String ipAddr,diskID;
    LinkedList<HDDIOTask> taskQueue=new LinkedList<HDDIOTask>();
    long idleTime=0;
    HDDIOTask currentTask;
    public HDD(String ipAddr,String diskID) {
        this.ipAddr=ipAddr;
        this.diskID=diskID;
    }
    public boolean isIdle(){
        return taskQueue.isEmpty();
    }
    public double work(double currentTime) {
        if (currentTask.getEndTime() == currentTime) {
            taskQueue.pollFirst();
            currentTask = taskQueue.getFirst();
        }
        if (taskQueue.isEmpty()) return 0;
        return taskQueue.getFirst().getEndTime();
    }
    public void addTask(double size,double speed){
        HDDIOTask task=new HDDIOTask(taskQueue.getLast(),size,speed);
        taskQueue.addLast(task);
    }
}
