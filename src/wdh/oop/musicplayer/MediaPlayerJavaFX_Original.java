package wdh.oop.musicplayer;

import java.io.File;

/**
 * Dies ist der (fake) JavaFX-Player.
 */
public class MediaPlayerJavaFX_Original {

    /* Statische Variablen */

    /* Statische Methoden */

    /* Objektvariablen / Attribute */
    private File mediaFile;

    /* Konstruktoren */
    public MediaPlayerJavaFX_Original(File mediaFile) {
        this.mediaFile = mediaFile;
    }

    /* Objektmethoden */
    public void play() {
        System.out.println("Playing " + mediaFile.getName());
    }

    public void pause() {
        System.out.println("Pausing " + mediaFile.getName());
    }

    public void stop() {
        System.out.println("Stopping " + mediaFile.getName());
    }

    /* Getter & Setter */
    public void setVolume(double volume) {
        System.out.println("Setting volume to " + volume);
    }

}