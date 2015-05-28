package logger;

import eduni.simjava.Sim_entity;

import java.io.IOException;

/**
 * Created by Amemiya on 5/28/15.
 */
public class LoggerEntity extends Sim_entity {
    public LoggerEntity() {
        super("logger");
    }

    @Override
    public void body() {
        sim_pause(Double.MAX_VALUE);
        try {
            Logger.output();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
