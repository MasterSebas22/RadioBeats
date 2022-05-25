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
public class StationList extends JPanel {

    private JLabel stationsListTittleLabel;
    private JScrollPane stationsListScrollView;
    private JTable stationsList;
    private JButton editStationButton;
    private JButton deleteStationButton;
    private JButton backButton;

    /**
     * Creates new form StationList
     */
    public StationList() {
        initComponents();
    }

    /**
     * Initalizes the JPanel components
     */
    private void initComponents() {
        stationsListTittleLabel = new JLabel();
        stationsListScrollView = new JScrollPane();
        stationsList = new JTable();
        editStationButton = new JButton();
        deleteStationButton = new JButton();
        backButton = new JButton();

        setLayout(null);
        setSize(new Dimension(515, 440));
        setPreferredSize(new Dimension(515, 440));

        stationsListTittleLabel.setFont(new Font("sansserif", 0, 24));
        stationsListTittleLabel.setText("Lista de Emisoras");
        stationsListTittleLabel.setBounds(150, 20, 220, 30);
        add(stationsListTittleLabel);

        stationsList.getTableHeader().setEnabled(false);
        stationsList.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre",
                StringEncoder.encodeStringUTF8("Modo de Transmisión"),
                StringEncoder.encodeStringUTF8("Genéro")
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        stationsListScrollView.setBounds(32, 70, 452, 270);
        stationsListScrollView.setViewportView(stationsList);
        add(stationsListScrollView);

        editStationButton.setText("Editar Emisora");
        editStationButton.setBounds(40, 357, 150, 30);
        editStationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(editStationButton);

        deleteStationButton.setText("Eliminar Emisora");
        deleteStationButton.setBounds(210, 357, 150, 30);
        deleteStationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(deleteStationButton);

        backButton.setText(StringEncoder.encodeStringUTF8("Atrás"));
        backButton.setBounds(380, 357, 95, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
                //TODO: Implement the other part
            }
        });
        add(backButton);
    }
}
