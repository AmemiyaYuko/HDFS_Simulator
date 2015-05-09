package core;

/**
 * Created by Amemiya on 5/9/15.
 */
public class HDDIOTask {
    private long startTime=0;
    private long endTime=0;
    private long id;
    HDDIOTask(long startTime,long size,int dataRate){
        this.startTime=startTime;
        this.endTime=size/dataRate+this.startTime;
    }
    HDDIOTask(HDDIOTask task,long size,int dataRate){
        this.startTime=task.getEndTime();
        this.endTime=size/dataRate+this.startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }


}
