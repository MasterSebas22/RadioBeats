package co.edu.unbosque.model.dao;

import java.util.List;

import co.edu.unbosque.model.dto.StationDTO;

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
public interface StationDAO {

    /**
     * Updates the radio beats' station information
     *
     * @param station sation instance to get updated
     * @param stationName name of the creating station
     * @param stationTransmitionType transmition type of the creating station
     * @param stationMusicGender gender of the music ofered by the creating station
     */
    void updateStation(StationDTO station, String stationName,
            String stationTransmitionType, String stationMusicGender);

    /**
     * Deletes the radio beats' station
     *
     * @param generalStationList the radio beats' general stations list
     */
    void deleteStation(List<StationDTO> generalStationList);
}
