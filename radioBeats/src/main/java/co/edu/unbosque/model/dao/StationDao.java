package co.edu.unbosque.model.dao;

import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.PlayList;
import co.edu.unbosque.model.Program;
import co.edu.unbosque.model.dto.StationDto;
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
public class StationDao implements StationDto {

    private String stationName;
    private String stationTransmitionType;
    private String stationMusicGender;
    private List<PlayList> stationPlayListsList;
    private Program stationProgram;

    /**
     * Creates a new StationDao instance
     *
     * @param stationName name of the creating station
     * @param stationTransmitionType transmition type of the creating station
     * @param stationMusicGender gender of the music ofered by the creating station
     */
    public StationDao(String stationName,
            String stationTransmitionType, String stationMusicGender) {
        this.stationName = stationName;
        this.stationTransmitionType = stationTransmitionType;
        this.stationMusicGender = stationMusicGender;
        this.stationPlayListsList = new ArrayList<>();
        this.stationProgram = null;
    }

    /**
     * Updates the radio beats' station information
     *
     * @param station sation instance to get updated
     * @param stationName name of the creating station
     * @param stationTransmitionType transmition type of the creating station
     * @param stationMusicGender gender of the music ofered by the creating station
     */
    @Override
    public void updateStation(StationDao station, String stationName,
            String stationTransmitionType, String stationMusicGender) {
        this.stationName = stationName;
        this.stationTransmitionType = stationTransmitionType;
        this.stationMusicGender = stationMusicGender;
    }

    /**
     * Deletes the radio beats' station
     *
     * @param generalStationList the radio beats' general stations list
     */
    @Override
    public void deleteStation(List<StationDao> generalStationList) {
        generalStationList.remove(this);
    }
}
