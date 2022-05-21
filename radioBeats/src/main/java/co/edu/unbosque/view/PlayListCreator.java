package co.edu.unbosque.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PlayListCreator extends JPanel {

    private JLabel playListCreatorTittleLabel;
    private JLabel playListNameLabel;
    private JLabel stationSelectorLabel;
    private JTextField playListNameField;
    private JComboBox<String> stationOptions;
    private JTable soundsList;
    private JScrollPane soundListPortView;
    private JButton acceptButton;

    public PlayListCreator() {
        initComponents();
    }

    private void initComponents() {
        playListCreatorTittleLabel = new JLabel();
        playListNameLabel = new JLabel();
        stationSelectorLabel = new JLabel();
        playListNameField = new JTextField();
        stationOptions = new JComboBox<>();
        soundListPortView = new JScrollPane();
        soundsList = new JTable();
        acceptButton = new JButton();

        setLayout(null);
        setSize(new Dimension(520, 460)); //Frame size 520x460
        setPreferredSize(new Dimension(520, 460));

        playListCreatorTittleLabel.setFont(new Font("sansserif", 0, 24));
        playListCreatorTittleLabel.setText("Crear PlayList");
        playListCreatorTittleLabel.setBounds(180, 20, 167, 29);
        add(playListCreatorTittleLabel);

        playListNameLabel.setText("Nombre");
        playListNameLabel.setBounds(130, 70, 60, 17);
        add(playListNameLabel);

        stationSelectorLabel.setText("Emisora");
        stationSelectorLabel.setBounds(340, 70, 53, 17);
        add(stationSelectorLabel);

        playListNameField.setBounds(70, 90, 180, 30);
        playListNameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });

        add(playListNameField);
        stationOptions.setBounds(280, 90, 180, 30);
        stationOptions.setModel(new DefaultComboBoxModel<>(
                    new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }
                    ));
        stationOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(stationOptions);

        soundsList.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Artista", "Genero"
            }
        ));
        soundListPortView.setBounds(30, 140, 460, 220);
        soundListPortView.setViewportView(soundsList);
        add(soundListPortView);

        acceptButton.setText("Aceptar");
        acceptButton.setBounds(200, 380, 100, 30);
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(acceptButton);
    }
}
