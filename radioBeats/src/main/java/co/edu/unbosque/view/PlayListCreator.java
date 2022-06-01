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
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.PlayList;
import co.edu.unbosque.model.Song;
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
public class PlayListCreator extends JPanel
    implements GraphicalComponentsTools {

    private JLabel playListCreatorTittleLabel;
    private JLabel playListNameLabel;
    private JLabel stationSelectorLabel;
    private JLabel generalSongsListLabel;
    private JTextField playListNameField;
    protected static JComboBox<Object> stationOptions;
    protected static JTable generalSongsList;
    private JScrollPane soundsListScrollView;
    private JButton acceptButton;
    private JButton cancelButton;
    protected static Song[] currentStationCompatibleSongs;

    /**
     * Creates new form PlayListCreator
     */
    public PlayListCreator() {
        initComponents();
    }

    /**
     * Initalizes the JPanel components
     */
    private void initComponents() {
        playListCreatorTittleLabel = new JLabel();
        playListNameLabel = new JLabel();
        stationSelectorLabel = new JLabel();
        generalSongsListLabel = new JLabel();
        playListNameField = new JTextField();
        stationOptions = new JComboBox<>();
        soundsListScrollView = new JScrollPane();
        generalSongsList = new JTable();
        acceptButton = new JButton();
        cancelButton = new JButton();
        currentStationCompatibleSongs = null;

        setLayout(null);
        setSize(new Dimension(520, 470));
        setPreferredSize(new Dimension(520, 470));

        playListCreatorTittleLabel.setFont(new Font("sansserif", 0, 24));
        playListCreatorTittleLabel.setText("Crear PlayList");
        playListCreatorTittleLabel.setBounds(180, 20, 167, 29);
        add(playListCreatorTittleLabel);

        playListNameLabel.setText("Nombre");
        playListNameLabel.setBounds(128, 70, 60, 17);
        add(playListNameLabel);

        stationSelectorLabel.setText("Emisora");
        stationSelectorLabel.setBounds(338, 70, 53, 17);
        add(stationSelectorLabel);

        generalSongsListLabel.setText("Lista General de Canciones");
        generalSongsListLabel.setBounds(178, 132, 270, 17);
        add(generalSongsListLabel);

        playListNameField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        playListNameField.setBounds(68, 90, 180, 30);
        playListNameField.getDocument().addDocumentListener(
                new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                uptadeLocalComponentEnabledState(acceptButton,
                        playListNameField, generalSongsList);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                uptadeLocalComponentEnabledState(acceptButton,
                        playListNameField, generalSongsList);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        add(playListNameField);

        stationOptions.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stationOptions.setBounds(278, 90, 180, 30);
        stationOptions.setModel(new DefaultComboBoxModel<>(
                    new String[] { "" }
                    ));
        stationOptions.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                if(!BaseAppFrame.stationsList.isEmpty()) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            currentStationCompatibleSongs =
                                updateTableContent(BaseAppFrame.generalSongList,
                                   BaseAppFrame.stationsList,
                                   stationOptions, generalSongsList);
                            uptadeLocalComponentEnabledState(acceptButton,
                                    playListNameField, generalSongsList);
                        }
                    });
                }
            }

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
        });
        add(stationOptions);

        generalSongsList.getTableHeader().setEnabled(false);
        generalSongsList.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "#------>*", "Nombre", "Artista",
                StringUtils.encodeStringUTF8("Genéro")
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
        generalSongsList.getColumnModel().getColumn(0).setPreferredWidth(1);
        generalSongsList.setCursor(new Cursor(Cursor.HAND_CURSOR));
        generalSongsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                uptadeLocalComponentEnabledState(acceptButton,
                        playListNameField, generalSongsList);
            }
        });
        soundsListScrollView.setBounds(30, 155, 460, 220);
        soundsListScrollView.setViewportView(generalSongsList);
        add(soundsListScrollView);

        acceptButton.setText("Aceptar");
        acceptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        acceptButton.setBounds(140, 390, 100, 30);
        acceptButton.setEnabled(false);
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                retriveAndCreateNewPlayList();
                BaseAppFrame.reloadFrameContent(-1);
                updateLocalComponetsOnExit();
            }
        });
        add(acceptButton);

        cancelButton.setText("Cancelar");
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.setBounds(280, 390, 100, 30);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
                updateLocalComponetsOnExit();
            }
        });
        add(cancelButton);
    }

    /**
     * Retrives the necesary information to create a new playList and creates it
     */
    private void retriveAndCreateNewPlayList() {
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

        BaseAppFrame.stationsList.get(stationOptions.getSelectedIndex())
            .getStationPlayListsList()
            .add(newPlayList);
    }

    /**
     * Updates a local panel's components on exit
     */
    @Override
    public void updateLocalComponetsOnExit() {
        playListNameField.setText(null);
        if(!BaseAppFrame.stationsList.isEmpty())
            stationOptions.setSelectedIndex(0);
    }
}
