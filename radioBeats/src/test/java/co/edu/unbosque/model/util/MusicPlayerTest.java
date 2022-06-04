package co.edu.unbosque.model.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.unbosque.model.PlayList;
import co.edu.unbosque.model.Song;
import co.edu.unbosque.model.dto.ProgramDTO;
import co.edu.unbosque.model.dto.StationDTO;
import co.edu.unbosque.util.MusicPlayer;

/**
 *
 * @author Bryan Baron
 *
 * @version 1.0
 *
 */
public class MusicPlayerTest {

    private MusicPlayer musicPlayer;
    private StationDTO testStationDTO;
    private PlayList testStationDTOPlaylist;
    private Song testStationDTOPlaylistSong;
    private ProgramDTO testStationDTOProgram;

    @BeforeEach
    public void setupTests() {
        musicPlayer = new MusicPlayer();
        testStationDTOPlaylistSong = Song.builder()
            .songName("Test Song")
            .songArtistName("Test Song Artist")
            .songGender("Rock/Metal")
            .songPath("static/sound/TestAudio.mp3")
            .dataUnitPath(null)
            .build();

        testStationDTOPlaylist = PlayList.builder()
            .playListName("Test PlayList")
            .playListSongs(new ArrayList<>())
            .build();
        testStationDTOPlaylist.getPlayListSongs()
            .add(testStationDTOPlaylistSong);

        testStationDTO = StationDTO.builder()
            .stationName("Test Station")
            .stationMusicGender("Rock/Metal")
            .stationTransmitionType("Streaming")
            .dataUnitPath(null)
            .build();
        testStationDTO.getStationPlayListsList().add(testStationDTOPlaylist);

         testStationDTOProgram = ProgramDTO.builder()
            .programPlayListsList(testStationDTO.getStationPlayListsList())
            .build();

         testStationDTO.setStationProgram(testStationDTOProgram);
    }

    @Test
    public void givenStationDTOMustPlaySongs() {
        List<Song> songsList = musicPlayer.playStationProgram(testStationDTO);
        assertEquals("Test Song", songsList.get(0).getSongName());
        assertThat(songsList).isNotEmpty();
    }

    @Test
    public void givenEmptyObjectMustFailSongPlaying() {
        List<Song> songsList = musicPlayer.playStationProgram(null);
        assertThat(songsList).isEmpty();
    }
}
