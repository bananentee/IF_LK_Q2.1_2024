package wdh.oop.musicplayer;

import java.io.File;

public interface MediaPlayer {
    public void play();
    public void pause();
    public void stop();
    public void setVolume(int volume); // only values from 0 to 100
}
