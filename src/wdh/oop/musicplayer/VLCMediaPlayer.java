/**
 * @author Sven Ibe
 * @version
 */

package wdh.oop.musicplayer;

import java.io.File;

public class VLCMediaPlayer implements MediaPlayer {

    /* static variables */


    /* static methods */


    /* attributes */
    private MediaPlayerVLC_Original mediaPlayer;
    private File file;

    /* constructors */
    VLCMediaPlayer(File file) {
        this.file = file;
        mediaPlayer = new MediaPlayerVLC_Original();
    }


    @Override
    public void play() {
        mediaPlayer.play(this.file);
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
    }

    @Override
    public void setVolume(int volume) {
        if (volume < 0 || volume > 100) {
            throw new IllegalArgumentException("Volume must be between 0 and 100");
        }
        mediaPlayer.setVolume(volume);
    }




    /* getter & setter */

}
