package co.edu.unbosque.model.dao;

import java.util.List;

import co.edu.unbosque.model.PlayList;
import co.edu.unbosque.model.Program;
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
@NoArgsConstructor
public class StationDao {

    private String stationName;
    private String sationTransmitionType;
    private String stationMusicGender;
    private List<PlayList> stationPlayListsList;
    private Program stationProgram;
}
