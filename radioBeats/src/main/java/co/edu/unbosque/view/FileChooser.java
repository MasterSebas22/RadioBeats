package co.edu.unbosque.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

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
public class FileChooser extends JPanel {

    private JFileChooser fileChooser;

    /**
     * Creates new form FileChooser
     */
    public FileChooser() {
        initComponents();
    }

    /**
     * Initalizes the JPanel components
     */
    private void initComponents() {
        fileChooser = new JFileChooser();

        setLayout(null);
        setSize(new Dimension(514, 346));
        setPreferredSize(new Dimension(514, 346));

        fileChooser.setBounds(0, 0, 514, 314);
        fileChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(fileChooser);
    }
}
