/**
 * @author Sven Ibe
 * @version
 */

package wdh.oop.musicplayer;

import java.io.File;

public class JFXMediaPlayer implements MediaPlayer {

    /* static variables */


    /* static methods */


    /* attributes */
    private MediaPlayerJavaFX_Original mediaPlayer;

    /* constructors */
    JFXMediaPlayer(File file) {
        mediaPlayer = new MediaPlayerJavaFX_Original(file);
    }


    @Override
    public void play() {
        mediaPlayer.play();
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
        mediaPlayer.setVolume((double) volume / 100d);
    }

    /* object methods */


    /* getter & setter */

}
