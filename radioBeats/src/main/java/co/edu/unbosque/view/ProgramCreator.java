package co.edu.unbosque.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.PlayList;
import co.edu.unbosque.model.Program;
import co.edu.unbosque.util.GraphicalComponentsTools;
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
public class ProgramCreator extends JPanel
    implements GraphicalComponentsTools {

    private JLabel createProgramTittleLabel;
    private JLabel stationSelectorLabel;
    private JLabel playListsListLabel;
    protected static JComboBox<Object> stationSelector;
    private JScrollPane playListListScrollView;
    protected static JTable playListsList;
    private JButton acceptButton;
    private JButton cancelButton;

    /**
     * Creates new form ProgramCreator
     */
    public ProgramCreator() {
        initComponents();
    }

    /**
     * Initalizes the JPanel components
     */
    private void initComponents() {
        createProgramTittleLabel = new JLabel();
        stationSelectorLabel = new JLabel();
        playListsListLabel = new JLabel();
        stationSelector = new JComboBox<>();
        playListListScrollView = new JScrollPane();
        playListsList = new JTable();
        acceptButton = new JButton();
        cancelButton = new JButton();

        setLayout(null);
        setSize(new Dimension(515, 510));
        setPreferredSize(new Dimension(515, 510));

        createProgramTittleLabel.setFont(new Font("sansserif", 0, 24));
        createProgramTittleLabel.setText(StringUtils
                .encodeStringUTF8("Crear Programación"));
        createProgramTittleLabel.setBounds(144, 20, 250, 29);
        add(createProgramTittleLabel);

        stationSelectorLabel.setText("Emisora");
        stationSelectorLabel.setBounds(236, 74, 60, 17);
        add(stationSelectorLabel);

        playListsListLabel.setText("Lista de PlayLists");
        playListsListLabel.setBounds(208, 145, 120, 17);
        add(playListsListLabel);

        stationSelector.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stationSelector.setModel(new DefaultComboBoxModel<>(
                    new String[] { "" }
                    ));
        stationSelector.setBounds(140, 96, 240, 30);
        stationSelector.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                if(!BaseAppFrame.stationsList.isEmpty()) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            updateTableContent(
                                    BaseAppFrame.stationsList.get(
                                        stationSelector.getSelectedIndex()
                                    ), stationSelector, playListsList);
                            uptadeLocalComponentEnabledState(acceptButton,
                                    playListsList);
                        }
                    });
                }
            }

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
        });
        add(stationSelector);

        playListsList.getTableHeader().setEnabled(false);
        playListsList.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "#-------->*", "Nombre",
            }
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch(columnIndex) {
                    case 0:
                      return Boolean.class;
                    default:
                      return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        });
        playListsList.getColumnModel().getColumn(0).setPreferredWidth(1);
        playListsList.getColumnModel().getColumn(1).setPreferredWidth(300);
        playListsList.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playListsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                uptadeLocalComponentEnabledState(acceptButton,
                        playListsList);
            }
        });
        playListListScrollView.setBounds(34, 171, 450, 240);
        playListListScrollView.setViewportView(playListsList);
        add(playListListScrollView);

        acceptButton.setText("Aceptar");
        acceptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        acceptButton.setBounds(144, 430, 100, 30);
        acceptButton.setEnabled(false);
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                retriveAndCreateNewProgram();
                BaseAppFrame.reloadFrameContent(-1);
                updateLocalComponetsOnExit();
            }
        });
        add(acceptButton);

        cancelButton.setText("Cancelar");
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.setBounds(280, 430, 100, 30);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
                updateLocalComponetsOnExit();
            }
        });
        add(cancelButton);
    }

    /**
     * Retrives the necesary information to create a new program and creates it
     */
    private void retriveAndCreateNewProgram() {
        Program newProgram = null;
        List<Integer> playListsSelectionsIndexes = new ArrayList<>();
        List<PlayList> newProgramPlayListsList = new ArrayList<>();
        DefaultTableModel playListsListTableModel =
            (DefaultTableModel) playListsList.getModel();

        for(int i = 0; i < playListsListTableModel.getRowCount(); i++) {
            if((Boolean) playListsListTableModel.getValueAt(i, 0)) {
                playListsSelectionsIndexes.add(i);
            }
        }

        for(int i = 0; i < playListsSelectionsIndexes.size(); i++) {
            newProgramPlayListsList.add(
                    BaseAppFrame.stationsList.get(
                        stationSelector.getSelectedIndex())
                            .getStationPlayListsList()
                                .get(playListsSelectionsIndexes.get(i)));
        }
        newProgram = new Program(newProgramPlayListsList);

        BaseAppFrame.stationsList.get(stationSelector.getSelectedIndex())
            .setStationProgram(newProgram);
    }

    /**
     * Updates a local panel's components on exit
     */
    @Override
    public void updateLocalComponetsOnExit() {
        if(!BaseAppFrame.stationsList.isEmpty())
            stationSelector.setSelectedIndex(0);
    }
}
