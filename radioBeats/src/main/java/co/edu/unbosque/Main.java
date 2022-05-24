package co.edu.unbosque;

import java.awt.EventQueue;

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
    private static BaseAppFrame bF = BaseAppFrame.getSingletonInstance();

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                bF.setVisible(true);
            }
        });
    }
}
