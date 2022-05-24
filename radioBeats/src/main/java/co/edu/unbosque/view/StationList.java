package co.edu.unbosque.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StationList extends JPanel {

    private JLabel stationsListTittleLabel;
    private JScrollPane stationsListScrollView;
    private JTable stationsList;
    private JButton editStationButton;
    private JButton deleteStationButton;
    private JButton backButton;

    public StationList() {
        initComponents();
    }

    private void initComponents() {
        stationsListTittleLabel = new JLabel();
        stationsListScrollView = new JScrollPane();
        stationsList = new JTable();
        editStationButton = new JButton();
        deleteStationButton = new JButton();
        backButton = new JButton();

        setLayout(null);
        setSize(new Dimension(515, 440)); //Frame size 515x440
        setPreferredSize(new Dimension(515, 440));

        stationsListTittleLabel.setFont(new Font("sansserif", 0, 24));
        stationsListTittleLabel.setText("Lista de Emisoras");
        stationsListTittleLabel.setBounds(150, 20, 220, 30);
        add(stationsListTittleLabel);

        stationsList.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        stationsListScrollView.setBounds(30, 70, 452, 270);
        stationsListScrollView.setViewportView(stationsList);
        add(stationsListScrollView);

        editStationButton.setText("Editar Emisora");
        editStationButton.setBounds(40, 360, 150, 30);
        editStationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(editStationButton);

        deleteStationButton.setText("Eliminar Emisora");
        deleteStationButton.setBounds(210, 360, 150, 30);
        deleteStationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(deleteStationButton);

        backButton.setText("Atras");
        backButton.setBounds(380, 360, 95, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(backButton);
    }
}
