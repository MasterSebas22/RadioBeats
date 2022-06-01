package co.edu.unbosque;

import co.edu.unbosque.view.BaseAppFrame;
import co.edu.unbosque.controller.GeneralController;

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
    private static GeneralController radioBeatsController =
        new GeneralController(BaseAppFrame.getSingletonInstance());

    public static void main(String[] args) {
        radioBeatsController.deployAplication();
    }
}
