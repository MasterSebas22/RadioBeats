package co.edu.unbosque.model;

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
public class Song {

    private String songName;
    private String songArtristName;
    private String songGender;
    private String songPath;
    private String dataUnitPath;

    /**
     * Creates a new Song instance
     *
     * @param songName name of the importing song
     * @param songArtistName artist's name of the importing song
     * @param songGender music gender of the importing song
     */
    public Song(String songName, String songArtristName, String songGender,
            String songPath) {
        this.songName = songName;
        this.songArtristName = songArtristName;
        this.songGender = songGender;
        this.songPath = songPath;
        this.dataUnitPath = null;
    }
}
