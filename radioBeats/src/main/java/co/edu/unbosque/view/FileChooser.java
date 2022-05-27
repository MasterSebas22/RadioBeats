package co.edu.unbosque.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import co.edu.unbosque.util.GraphicalComponentsTools;

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
public class FileChooser extends JPanel implements GraphicalComponentsTools {

    private JFileChooser fileChooser;
    private JPanel fileChooserPanel;
    private JButton fileChooserButton;

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
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        fileChooser = new JFileChooser();
        fileChooserPanel = null;
        fileChooserButton = null;

        setLayout(null);
        setSize(new Dimension(514, 346));
        setPreferredSize(new Dimension(514, 346));

        fileChooser.setBounds(0, 0, 514, 314);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.removeChoosableFileFilter(
            fileChooser.getChoosableFileFilters()[0]);
        fileChooser.setFileFilter(new FileNameExtensionFilter(
                    "Ficheros de audio", "mp3", "wav"));
        fileChooserPanel = (JPanel) fileChooser.getComponent(0);
        fileChooserPanel = (JPanel) fileChooserPanel.getComponent(0);

        //Setting main buttons' event
        fileChooserPanel = (JPanel) fileChooser.getComponent(3);
        fileChooserPanel = (JPanel) fileChooserPanel.getComponent(3);

        //Accept button
        fileChooserButton = (JButton) fileChooserPanel.getComponent(1);
        fileChooserButton.setText("Aceptar");
        fileChooserButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fileChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SongImporter.fileSelectedPath = fileChooser.getSelectedFile()
                        .getPath();
                uptadeLocalComponentEnabledState(SongImporter.acceptButton,
                        SongImporter.songNameField,
                        SongImporter.artistNameField,
                        SongImporter.fileSelectedPath);
                BaseAppFrame.reloadFrameContent(6);
            }
        });

        //Cancel button
        fileChooserButton = new JButton();
        fileChooserButton.setText("Cancelar");
        fileChooserButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fileChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
            }
        });
        fileChooserPanel.add(fileChooserButton);

        //Removing open button
        fileChooserPanel.remove(0);

        add(fileChooser);
    }
}
