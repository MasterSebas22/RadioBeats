package co.edu.unbosque;

import co.edu.unbosque.controller.MusicPlayerController;
import co.edu.unbosque.view.BaseAppFrame;

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
public class Main {
    private static MusicPlayerController radioBeatsController =
        new MusicPlayerController(BaseAppFrame.getSingletonInstance());

    public static void main(String[] args) {
        radioBeatsController.deployAplication();
    }
}
