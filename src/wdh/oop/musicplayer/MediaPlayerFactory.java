/**
 * @author Sven Ibe
 * @version
 */

package wdh.oop.musicplayer;

import java.io.File;

public class MediaPlayerFactory {

    /* static variables */


    /* static methods */
    public static MediaPlayer newMediaPlayer(File file) {
        String os = System.getProperty("os.name");
        if (os.contains("Linux")) {
            return new VLCMediaPlayer(file);
        }
        return new JFXMediaPlayer(file);
    }

    public static void main(String[] args) {
        newMediaPlayer(new File("src/wdh/oop/musicplayer/Wdh. Modellierung.pdf")).play();
    }

    /* attributes */


    /* constructors */
    private MediaPlayerFactory() {}


    /* object methods */


    /* getter & setter */

}
