package co.edu.unbosque.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.PlayList;
import co.edu.unbosque.model.Song;
import co.edu.unbosque.model.dto.StationDTO;
import co.edu.unbosque.view.BaseAppFrame;
import co.edu.unbosque.view.ProgramPlayer;
import javazoom.jl.player.Player;

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
public class MusicPlayer {

    private static FileInputStream fileInputStream;
    private static BufferedInputStream bufferedInputStream;
    private static Player musicPlayer;
    private static MusicPlayerThread musicPlayerThread;

    /**
     * Plays a desired station's program playList list
     *
     * @param station owner instance of the playlist list
     * @return the general songs list beeing played
     */
    public static List<Song> playStationProgram(StationDTO station) {
        List<Song> generalPlayListsSongs = new ArrayList<>();
        if(station != null) {
            for(PlayList playList : station.getStationPlayListsList()) {
                generalPlayListsSongs.addAll(playList.getPlayListSongs());
            }
            musicPlayerThread = new MusicPlayer
                .MusicPlayerThread(station, generalPlayListsSongs);
            musicPlayerThread.start();
        }

        return generalPlayListsSongs;
    }

    /**
     * Stops the music player
     */
    public static void stopStationProgram() {
        musicPlayer.close();
    }

    private static class MusicPlayerThread extends Thread
            implements GraphicalComponentsTools {

        StationDTO playListListOwner;
        List<Song> threadDependencyLits;

        /**
         * Creates a new songPlayerThread instance
         *
         * @param playListListOwner Station owner of the playList list
         */
        public MusicPlayerThread(StationDTO playListListOwner,
                List<Song> threadDependencyLits) {
            this.playListListOwner = playListListOwner;
            this.threadDependencyLits = threadDependencyLits;
        }

        @Override
        public void run() {
            try {
                for(Song song : threadDependencyLits) {
                    File songFile = new File(song.getSongPath());
                    fileInputStream = new FileInputStream(songFile);
                    bufferedInputStream = new BufferedInputStream(fileInputStream);

                    musicPlayer = new Player(bufferedInputStream);
                    musicPlayer.play();
                    if(!musicPlayer.isComplete()) {
                        wait();
                    } else continue;
                }
            } catch (Exception e) {
            } finally {
                playListListOwner.getStationProgram()
                    .deleteStation(playListListOwner);
                ProgramPlayer.programedStationList = updateTableContent(
                    BaseAppFrame.stationsList,
                    ProgramPlayer.programsList, 2);
                ProgramPlayer.programsList.setEnabled(true);
                ProgramPlayer.playButton.setEnabled(false);
                ProgramPlayer.stopButton.setEnabled(false);
                ProgramPlayer.deleteButton.setEnabled(false);
                ProgramPlayer.backButton.setEnabled(true);
            }
        }
    }
}
