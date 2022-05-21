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

public class StationCreator extends JPanel {

    private JLabel stationCreationTittleLabel;
    private JLabel stationNameLabel;
    private JLabel stationTransmitionLabel;
    private JLabel stationMusicGenLabel;
    private JTextField stationNameField;
    private JComboBox<String> stationMusicGenOptions;
    private JComboBox<String> transmistionModeOptions;
    private JButton acceptButton;

    public StationCreator() {
        initComponents();
    }

    private void initComponents() {
        stationCreationTittleLabel = new JLabel();
        stationNameLabel = new JLabel();
        stationTransmitionLabel = new JLabel();
        stationMusicGenLabel = new JLabel();
        stationNameField = new JTextField();
        transmistionModeOptions = new JComboBox<>();
        stationMusicGenOptions = new JComboBox<>();
        acceptButton = new JButton();

        setLayout(null);
        setSize(new Dimension(430, 290)); //Frame size 430x290
        setPreferredSize(new Dimension(430, 290));

        stationCreationTittleLabel.setFont(new Font("sansserif", 0, 24));
        stationCreationTittleLabel.setText("Crear Emisora");
        stationCreationTittleLabel.setBounds(130, 20, 180, 40);
        add(stationCreationTittleLabel);

        stationNameLabel.setText("Nombre");
        stationNameLabel.setBounds(90, 80, 60, 17);
        add(stationNameLabel);

        stationTransmitionLabel.setText("Modo de transmision");
        stationTransmitionLabel.setBounds(240, 80, 140, 17);
        add(stationTransmitionLabel);

        stationMusicGenLabel.setText("Genero");
        stationMusicGenLabel.setBounds(190, 140, 50, 17);
        add(stationMusicGenLabel);

        transmistionModeOptions.setCursor(new Cursor(Cursor.HAND_CURSOR));
        transmistionModeOptions.setBounds(220, 100, 170, 30);
        transmistionModeOptions.setModel(new DefaultComboBoxModel<>(
                    new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }
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
                    new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }
                    ));
        stationMusicGenOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(stationMusicGenOptions);

        acceptButton.setText("Aceptar");
        acceptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        acceptButton.setBounds(168, 220, 100, 30);
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(acceptButton);
    }
}
