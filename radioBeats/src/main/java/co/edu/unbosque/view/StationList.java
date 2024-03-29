package co.edu.unbosque.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
public class StationList extends JPanel implements GraphicalComponentsTools {

    private JLabel stationsListTittleLabel;
    private JScrollPane stationsListScrollView;
    protected static JTable stationsList;
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
            new Object [][] {},
            new String [] {
                "Nombre",
                StringUtils.encodeStringUTF8("Modo de Transmisión"),
                StringUtils.encodeStringUTF8("Genéro")
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        stationsList.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stationsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(stationsList.getSelectedRow() == -1) {
                    editStationButton.setEnabled(false);
                    deleteStationButton.setEnabled(false);
                } else {
                    editStationButton.setEnabled(true);
                    deleteStationButton.setEnabled(true);
                }
            }
        });
        stationsListScrollView.setBounds(32, 70, 452, 270);
        stationsListScrollView.setViewportView(stationsList);
        add(stationsListScrollView);

        editStationButton.setText("Editar Emisora");
        editStationButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editStationButton.setBounds(40, 357, 150, 30);
        editStationButton.setEnabled(false);
        editStationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                graphicStationsActions(1);
                updateLocalComponetsOnExit();
            }
        });
        add(editStationButton);

        deleteStationButton.setText("Eliminar Emisora");
        deleteStationButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteStationButton.setBounds(210, 357, 150, 30);
        deleteStationButton.setEnabled(false);
        deleteStationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                graphicStationsActions(2);
                updateLocalComponetsOnExit();
            }
        });
        add(deleteStationButton);

        backButton.setText(StringUtils.encodeStringUTF8("Atrás"));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBounds(380, 357, 95, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
                updateLocalComponetsOnExit();
            }
        });
        add(backButton);
    }

    /**
     * Performes crud actions over the station list and represents it graphically
     *
     * @param crudActionOption option of the desired action to perform
     */
    private void graphicStationsActions(int crudActionOption) {
        switch(crudActionOption) {
            case 1:
                StationManager.editionModeActivated = true;
                StationManager.editingStation =
                    BaseAppFrame.stationsList.get(
                            stationsList.getSelectedRow());
                StationManager.setupStationEditionMode(1);
                break;
            case 2:
                RadioBeatsDataManager.deleteDataUnit(BaseAppFrame.stationsList
                    .get(stationsList.getSelectedRow()).getDataUnitPath());

                BaseAppFrame.stationsList
                    .get(stationsList.getSelectedRow())
                    .deleteStation(BaseAppFrame.stationsList);
                break;
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                updateTableContent(
                    BaseAppFrame.stationsList,
                    StationList.stationsList, 1);
            }
        });
    }

    /**
     * Updates a local panel's components on exit
     */
    @Override
    public void updateLocalComponetsOnExit() {
        editStationButton.setEnabled(false);
        deleteStationButton.setEnabled(false);
    }
}
