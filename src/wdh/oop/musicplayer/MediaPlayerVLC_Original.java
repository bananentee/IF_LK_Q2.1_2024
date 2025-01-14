package wdh.oop.musicplayer;

import java.io.File;

/**
 * Dies ist der (fake) VLC-Player.
 */
public class MediaPlayerVLC_Original {

    /* Statische Variablen */

    /* Statische Methoden */

    /* Objektvariablen / Attribute */

    /* Konstruktoren */
    public MediaPlayerVLC_Original() {
        // nix
    }

    /* Objektmethoden */
    public void play(File mediaFile) {
        System.out.println("Playing " + mediaFile);
    }

    public void pause() {
        System.out.println("Pause");
    }

    public void stop() {
        System.out.println("Stop");
    }

    /* Getter & Setter */
    public void setVolume(int volume) {
        System.out.println("Set volume to " + volume);
    }

}