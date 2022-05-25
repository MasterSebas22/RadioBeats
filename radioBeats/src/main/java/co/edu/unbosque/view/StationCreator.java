package co.edu.unbosque.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Cursor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.unbosque.util.StringEncoder;

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
public class StationCreator extends JPanel {

    private JLabel stationCreationTittleLabel;
    private JLabel stationNameLabel;
    private JLabel stationTransmitionLabel;
    private JLabel stationMusicGenLabel;
    private JTextField stationNameField;
    private JComboBox<String> stationMusicGenOptions;
    private JComboBox<String> transmistionModeOptions;
    private JButton acceptButton;
    private JButton backButton;

    /**
     * Creates new form StationCreator
     */
    public StationCreator() {
        initComponents();
    }

    /**
     * Initalizes the jFrame components
     */
    private void initComponents() {
        stationCreationTittleLabel = new JLabel();
        stationNameLabel = new JLabel();
        stationTransmitionLabel = new JLabel();
        stationMusicGenLabel = new JLabel();
        stationNameField = new JTextField();
        transmistionModeOptions = new JComboBox<>();
        stationMusicGenOptions = new JComboBox<>();
        acceptButton = new JButton();
        backButton = new JButton();

        setLayout(null);
        setSize(new Dimension(433, 300));
        setPreferredSize(new Dimension(433, 300));

        stationCreationTittleLabel.setFont(new Font("sansserif", 0, 24));
        stationCreationTittleLabel.setText("Crear Emisora");
        stationCreationTittleLabel.setBounds(130, 20, 180, 40);
        add(stationCreationTittleLabel);

        stationNameLabel.setText("Nombre");
        stationNameLabel.setBounds(95, 80, 60, 17);
        add(stationNameLabel);

        stationTransmitionLabel.setText(StringEncoder
                .encodeStringUTF8("Modo de transmisión"));
        stationTransmitionLabel.setBounds(240, 80, 140, 17);
        add(stationTransmitionLabel);

        stationMusicGenLabel.setText(StringEncoder.encodeStringUTF8("Género"));
        stationMusicGenLabel.setBounds(190, 140, 50, 17);
        add(stationMusicGenLabel);

        transmistionModeOptions.setCursor(new Cursor(Cursor.HAND_CURSOR));
        transmistionModeOptions.setBounds(220, 100, 170, 30);
        transmistionModeOptions.setModel(new DefaultComboBoxModel<>(
                    new String[] { "AM", "FM", "Streaming" }
                    ));
        transmistionModeOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(transmistionModeOptions);

        stationNameField.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stationNameField.setBounds(40, 100, 170, 30);
        stationNameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(stationNameField);

        stationMusicGenOptions.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stationMusicGenOptions.setBounds(126, 160, 180, 30);
        stationMusicGenOptions.setModel(new DefaultComboBoxModel<>(
                    new String[] {
                        "Rock/Metal", "HipHop/Rap", "Pop", "Reaggeton",
                        "Vallenato", "Reagge", "Salsa/Merengue", "Otro" }
                    ));
        stationMusicGenOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(stationMusicGenOptions);

        acceptButton.setText("Aceptar");
        acceptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        acceptButton.setBounds(102, 217, 100, 30);
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
                //TODO: Implement the other part
            }
        });
        add(acceptButton);

        backButton.setText(StringEncoder.encodeStringUTF8("Atrás"));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBounds(230, 217, 100, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
            }
        });
        add(backButton);
    }
}
