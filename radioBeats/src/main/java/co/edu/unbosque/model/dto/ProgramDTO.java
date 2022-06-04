package co.edu.unbosque.model.dto;

import java.util.List;

import co.edu.unbosque.model.PlayList;
import co.edu.unbosque.model.dao.ProgramDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
@NoArgsConstructor
@Builder
public class ProgramDTO implements ProgramDAO {

    private List<PlayList> programPlayListsList;

    /**
     * Creates a new Program instance
     *
     * @param programPlayListsList creating program playlist list
     */
    public ProgramDTO(List<PlayList> programPlayListsList) {
        this.programPlayListsList = programPlayListsList;
    }

    /**
     * Deletes the radio beats' station's program
     *
     * @param stationProgramOwner program deleting station owner
     */
    @Override
    public void deleteStation(StationDTO stationProgramOwner) {
        stationProgramOwner.setStationProgram(null);
    }
}
