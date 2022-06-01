package co.edu.unbosque.controller;

import java.awt.EventQueue;

import co.edu.unbosque.view.BaseAppFrame;
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
public class GeneralController {

    private BaseAppFrame baseAppFrame;

    /**
     * Creates new UnnameController instance
     *
     * @param baseAppFrame BaseAppFrame intsance to be used
     */
    public GeneralController(BaseAppFrame baseAppFrame) {
        this.baseAppFrame = baseAppFrame;
    }

    /**
     * Delpoys the aplication
     */
    public void deployAplication() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                baseAppFrame.setVisible(true);
            }
        });
    }
}
