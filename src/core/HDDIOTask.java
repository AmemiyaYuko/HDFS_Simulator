package core;

/**
 * Created by Amemiya on 5/9/15.
 */
public class HDDIOTask {
    private double startTime=0;
    private double endTime=0;
    private long id;
    HDDIOTask(double startTime,double size,double speed){
        this.startTime=startTime;
        this.endTime=size/speed+this.startTime;
    }
    HDDIOTask(HDDIOTask task,double size,double speed){
        this.startTime=task.getEndTime();
        this.endTime=size/speed+this.startTime;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }


}
