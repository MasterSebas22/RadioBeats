package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;

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
public class Program {

    private List<PlayList> programPlayListsList;

    /**
     * Creates a new Program instance
     *
     * @param creating program playlist list
     */
    public Program(List<PlayList> programPlayListsList) {
        this.programPlayListsList = programPlayListsList;
    }
}
