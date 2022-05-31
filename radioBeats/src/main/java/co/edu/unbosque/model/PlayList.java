package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.util.RadioBeatsDataManager;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PlayList {

    private String playListName;
    private List<Song> playListSongs;

    /**
     * Creates a new PlayList instance
     *
     * @param playListName name of the creating playList
     * @param playListSong songs list of the creating playList
     */
    public PlayList(String playListName) {
        this.playListName = playListName;
        this.playListSongs = RadioBeatsDataManager
            .retriveDataUnitsAsObjects(Song.class);
    }
}
