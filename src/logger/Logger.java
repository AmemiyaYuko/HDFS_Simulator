package logger;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Amemiya on 5/9/15.
 */
public class Logger {
    public static ArrayList<Track> tracks = new ArrayList<Track>();
    private static int lastTrackID = -1;

    public int newTrack(String name) {
        lastTrackID++;
        tracks.add(new Track(name));
        return lastTrackID;
    }

    public void output() throws IOException {
        for (int i = 0; i < tracks.size(); i++)
            tracks.get(i).output();
    }

    public void newEvent(int trackID, String name, double consumption) {
        tracks.get(trackID).newEvent(name, consumption);
    }

}
