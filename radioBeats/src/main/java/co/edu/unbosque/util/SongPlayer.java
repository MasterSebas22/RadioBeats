package co.edu.unbosque.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import co.edu.unbosque.model.PlayList;
import co.edu.unbosque.model.Song;

/**
 *
 * @author Bryan Baron
 * @author Juan Avila
 * @author Juan Tarazona
 * @author Sebastian Carrera
 *
 * @version 1.0
 *
 */
public class SongPlayer {

    private static AudioInputStream audioStream;
    private static Clip audioClip;

    /**
     * Plays a desired playlist list
     *
     * @param programplayListsList a program's playlist list ready to play
     */
    public static void playSongList(List<PlayList> programPlayListsList) {
        List<Song> generalPlayListsSongs = new ArrayList<>();
        for(PlayList playList : programPlayListsList) {
            generalPlayListsSongs.addAll(playList.getPlayListSongs());
        }

        // Thread songPlayertThread = new Thread("Song Player Thread") {
        //     @Override
        //     public void run() {
        //     }
        //
        //     @Override
        //     public void start() {
        //         run();
        //     }
        // };
        // songPlayertThread.start();
        try {
            for(Song song : generalPlayListsSongs) {
                audioStream = AudioSystem.getAudioInputStream(
                        new File(song.getSongPath()).getAbsoluteFile());
                System.out.println(audioStream.getFormat());
                audioClip = AudioSystem.getClip();
                audioClip.open(audioStream);
                audioClip.start();
            }

        } catch(UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch(LineUnavailableException e) {
            e.printStackTrace();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicia o detiene el proceso de reporducción del recurso de audio indicado
     *
     * @param option elección de activación o desactivción del sonido
     */
    public void playStopSong(int option) {
        //Inicio o interrupción de la música de fondo
        switch(option) {
            case 0:
                audioClip.start();
                break;
            case 1:
                audioClip.stop();
                audioClip.flush();
        }
    }

    /**
     * Reinica el proceso de reporducción del recurso de audio indicado
     */
    public void restartSong() {
        audioClip.setMicrosecondPosition(0);
    }
}
