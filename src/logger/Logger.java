package logger;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Amemiya on 5/9/15.
 */
public class Logger {
    public static ArrayList<Track> tracks = new ArrayList<Track>();
    private static int lastTrackID = -1;

    public static int newTrack(String name, double startClock) {
        lastTrackID++;
        tracks.add(new Track(name, startClock));
        return lastTrackID;
    }

    public static void output() throws IOException {
        for (int i = 0; i < tracks.size(); i++)
            tracks.get(i).output();
    }

    public static void newEvent(int trackID, String name, double clock) {
        tracks.get(trackID).newEvent(name, clock);
    }

}
