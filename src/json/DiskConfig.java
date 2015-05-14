package json;

/**
 * Created by Amemiya on 5/12/15.
 */
public class DiskConfig {
    private double capacity, readSpeed, writeSpeed, seekTime;
    private String id;

    DiskConfig(String id) {
        this.id = id;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity.doubleValue();
    }

    public void setSpeed(Double readSpeed, Double writeSpeed) {
        this.readSpeed = readSpeed.doubleValue();
        this.writeSpeed = writeSpeed.doubleValue();
    }

    public void setSeekTime(Double seekTime) {
        this.seekTime = seekTime.doubleValue();
    }

    public String getId() {
        return id;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getReadSpeed() {
        return readSpeed;
    }

    public double getWriteSpeed() {
        return writeSpeed;
    }

    public double getSeekTime() {
        return seekTime;
    }
}
