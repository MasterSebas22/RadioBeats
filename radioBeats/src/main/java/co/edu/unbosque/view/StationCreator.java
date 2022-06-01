package co.edu.unbosque.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import co.edu.unbosque.model.dto.StationDTO;
import co.edu.unbosque.util.DateTimeGenerator;
import co.edu.unbosque.util.GraphicalComponentsTools;
import co.edu.unbosque.util.RadioBeatsDataManager;
import co.edu.unbosque.util.StringUtils;

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
public class StationCreator extends JPanel
    implements GraphicalComponentsTools {

    private JLabel stationCreationTittleLabel;
    private JLabel stationNameLabel;
    private JLabel stationTransmitionLabel;
    private JLabel stationMusicGenLabel;
    private JTextField stationNameField;
    private JComboBox<String> stationMusicGenOptions;
    private JComboBox<String> transmistionModeOptions;
    private JButton acceptButton;
    private JButton cancelButton;

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
        cancelButton = new JButton();

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

        stationTransmitionLabel.setText(StringUtils
                .encodeStringUTF8("Modo de Transmisión"));
        stationTransmitionLabel.setBounds(240, 80, 140, 17);
        add(stationTransmitionLabel);

        stationMusicGenLabel.setText(StringUtils.encodeStringUTF8("Género"));
        stationMusicGenLabel.setBounds(190, 140, 50, 17);
        add(stationMusicGenLabel);

        stationNameField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        stationNameField.setBounds(40, 100, 170, 30);
        stationNameField.getDocument().addDocumentListener(
                new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                uptadeLocalComponentEnabledState(acceptButton,
                        stationNameField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                uptadeLocalComponentEnabledState(acceptButton,
                        stationNameField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        add(stationNameField);

        transmistionModeOptions.setCursor(new Cursor(Cursor.HAND_CURSOR));
        transmistionModeOptions.setBounds(220, 100, 170, 30);
        transmistionModeOptions.setModel(new DefaultComboBoxModel<>(
                    new String[] { "AM", "FM", "Streaming" }
                    ));
        add(transmistionModeOptions);


        stationMusicGenOptions.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stationMusicGenOptions.setBounds(126, 160, 180, 30);
        stationMusicGenOptions.setModel(new DefaultComboBoxModel<>(
                    new String[] {
                        "Rock/Metal", "HipHop/Rap", "Pop", "Reaggeton",
                        "Vallenato", "Reagge", "Salsa/Merengue", "Otro" }
                    ));
        add(stationMusicGenOptions);

        acceptButton.setText("Aceptar");
        acceptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        acceptButton.setBounds(102, 217, 100, 30);
        acceptButton.setEnabled(false);
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                retriveAndCreateNewStation();
                BaseAppFrame.reloadFrameContent(-1);
                updateLocalComponetsOnExit();
            }
        });
        add(acceptButton);

        cancelButton.setText("Cancelar");
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.setBounds(230, 217, 100, 30);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
                updateLocalComponetsOnExit();
            }
        });
        add(cancelButton);
    }

    /**
     * Updates a local panel's components on exit
     */
    @Override
    public void updateLocalComponetsOnExit() {
        stationNameField.setText(null);
        transmistionModeOptions.setSelectedIndex(0);
        stationMusicGenOptions.setSelectedIndex(0);
    }

    /**
     * Retrives the necesary information to create a new station and creates it
     */
    private void retriveAndCreateNewStation() {
        StationDTO newStation = new StationDTO(
                StringUtils.sanitizeStringCharacters(
                    stationNameField.getText().trim()),
                transmistionModeOptions.getSelectedItem()
                    .toString(),
                stationMusicGenOptions.getSelectedItem()
                    .toString());

        RadioBeatsDataManager.createDataUnit(2,
                String.format("%s_%s",
                    StringUtils.sanitizeStringCharacters(
                        stationNameField.getText().trim()),
                    DateTimeGenerator.retriveLocalDate()), newStation);
        BaseAppFrame.stationsList.add(newStation);
    }
}
