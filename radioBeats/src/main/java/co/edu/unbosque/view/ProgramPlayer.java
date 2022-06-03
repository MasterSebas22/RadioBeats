package co.edu.unbosque.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.dto.StationDTO;
import co.edu.unbosque.util.GraphicalComponentsTools;
import co.edu.unbosque.util.SongPlayer;
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
public class ProgramPlayer extends JPanel
    implements GraphicalComponentsTools {

    private JLabel programsListLabel;
    private JButton backButton;
    private JScrollPane programsListScrollView;
    private JButton playButton;
    private JLabel playProgramTittleLabel;
    protected static JTable programsList;
    private JButton stopButton;
    protected static List<StationDTO> programedStationList;

    /**
     * Creates new form ProgramReproductor
     */
    public ProgramPlayer() {
        initComponents();
    }

    /**
     * Initalizes the JPanel components
     */
    private void initComponents() {
        programsListLabel = new JLabel();
        playProgramTittleLabel = new JLabel();
        programsListScrollView = new JScrollPane();
        programsList = new JTable();
        playButton = new JButton();
        stopButton = new JButton();
        backButton = new JButton();
        programedStationList = null;

        setLayout(null);
        setSize(new Dimension(515, 444));
        setPreferredSize(new Dimension(515, 444));

        playProgramTittleLabel.setFont(new Font("sansserif", 0, 24));
        playProgramTittleLabel.setText(StringUtils
                .encodeStringUTF8("Reproducir Programación"));
        playProgramTittleLabel.setBounds(113, 20, 310, 30);
        add(playProgramTittleLabel);

        programsListLabel.setText(StringUtils

                .encodeStringUTF8("Lista de Programaciones"));
        programsListLabel.setBounds(184, 60, 180, 30);
        add(programsListLabel);

        programsList.getTableHeader().setEnabled(false);
        programsList.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {
                StringUtils.encodeStringUTF8(
                        "Emisoras con Programación"),
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        programsList.setCursor(new Cursor(Cursor.HAND_CURSOR));
        programsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(programsList.getSelectedRow() == -1) {
                    playButton.setEnabled(false);
                    stopButton.setEnabled(false);
                } else {
                    playButton.setEnabled(true);
                    stopButton.setEnabled(true);
                }
            }
        });
        programsListScrollView.setBounds(30, 95, 450, 250);
        programsListScrollView.setViewportView(programsList);
        add(programsListScrollView);

        playButton.setText("Reproducir");
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playButton.setBounds(77, 362, 100, 30);
        playButton.setEnabled(false);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backButton.setEnabled(false);
                programsList.setEnabled(false);
                retriveAndPerformPlayerActions();
                //TODO: Player's play option here
            }
        });
        add(playButton);

        stopButton.setText("Detener");
        stopButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stopButton.setBounds(207, 362, 100, 30);
        stopButton.setEnabled(false);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backButton.setEnabled(true);
                programsList.setEnabled(true);
                //TODO: Player's stop option here
            }
        });
        add(stopButton);

        backButton.setText(StringUtils.encodeStringUTF8("Atrás"));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBounds(337, 362, 100, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
                updateLocalComponetsOnExit();
            }
        });
        add(backButton);
    }

    /**
     * Updates a local panel's components on exit
     */
    @Override
    public void updateLocalComponetsOnExit() {
        playButton.setEnabled(false);
        stopButton.setEnabled(false);
    }

    /**
     * Retrives the necesary information to perform the player's play and stop
     * actions
     */
    private void retriveAndPerformPlayerActions() {
        SongPlayer.playSongList(
                programedStationList.get(programsList.getSelectedRow())
                .getStationPlayListsList()
        );
    }
}
