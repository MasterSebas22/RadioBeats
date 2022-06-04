package co.edu.unbosque.model.dao;

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
@FunctionalInterface
public interface ProgramDAO {

    /**
     * Deletes the radio beats' station's program
     *
     * @param stationProgramOwner program deleting station owner
     */
    void deleteStation(StationDTO stationProgramOwner);
}
