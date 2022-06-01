package co.edu.unbosque.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private JButton backButton;

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
        backButton = new JButton();

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
                BaseAppFrame.reloadFrameContent(-1);
                //TODO: Implement the other part
            }
        });
        add(acceptButton);

        backButton.setText("Cancelar");
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBounds(280, 430, 100, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
            }
        });
        add(backButton);
    }

    /**
     * Retrives the necesary information to create a new program and creates it
     */
    private void retriveAndCreateNewProgram() {
        PlayList newPlayList = new PlayList(
                StringUtils.encodeStringUTF8(
                    playListNameField.getText().trim())
                );
        List<Integer> songsSelectionsIndexes = new ArrayList<>();
        List<Song> newPlayListSongsList = new ArrayList<>();
        DefaultTableModel songListTableModel =
            (DefaultTableModel) generalSongsList.getModel();

        for(int i = 0; i < songListTableModel.getRowCount(); i++) {
            if((Boolean) songListTableModel.getValueAt(i, 0)) {
                songsSelectionsIndexes.add(i);
            }
        }

        for(int i = 0; i < songsSelectionsIndexes.size(); i++) {
            newPlayListSongsList.add(
                    currentStationCompatibleSongs[songsSelectionsIndexes.get(i)]);
        }
        newPlayList.setPlayListSongs(newPlayListSongsList);

        RadioBeatsDataManager.createDataUnit(3,
                String.format("%s_playList_%d_%s",
                    BaseAppFrame.stationsList
                        .get(stationOptions.getSelectedIndex()).getStationName(),
                    BaseAppFrame.stationsList
                        .get(stationOptions.getSelectedIndex())
                            .getStationPlayListsList().size(),
                    DateTimeGenerator.retriveLocalDate()
                ),
                newPlayList);

        BaseAppFrame.stationsList.get(stationOptions.getSelectedIndex())
            .getStationPlayListsList()
            .add(newPlayList);
    }
}
