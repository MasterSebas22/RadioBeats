package co.edu.unbosque.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
public class MainMenu extends JPanel implements GraphicalComponentsTools {

    private JLabel mainMenuTitleLabel;
    private JButton createStationButton;
    private JButton createPlayListButton;
    private JButton seeStationListButton;
    private JButton createProgramButton;
    private JButton playProgramButton;

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
    }

    /**
     * Initalizes the JPanel components
     */
    private void initComponents() {
        mainMenuTitleLabel = new JLabel();
        createStationButton = new JButton();
        createPlayListButton = new JButton();
        seeStationListButton = new JButton();
        createProgramButton = new JButton();
        playProgramButton = new JButton();

        setLayout(null);
        setSize(new Dimension(430, 340));
        setPreferredSize(new Dimension(430, 340));

        mainMenuTitleLabel.setFont(new Font("sansserif", 0, 36));
        mainMenuTitleLabel.setText("RadioBeats");
        mainMenuTitleLabel.setBounds(120, 30, 204, 50);
        add(mainMenuTitleLabel);

        createStationButton.setText("Crear Emisora");
        createStationButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createStationButton.setBounds(40, 110, 170, 50);
        createStationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(1);
            }
        });
        add(createStationButton);

        createPlayListButton.setText("Crear PlayList");
        createPlayListButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createPlayListButton.setBounds(220, 110, 170, 50);
        createPlayListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(2);
                if(!BaseAppFrame.stationsList.isEmpty()) {
                    updateComboBoxElementList(
                            BaseAppFrame.stationsList,
                            PlayListCreator.stationOptions);
                    PlayListCreator.currentStationCompatibleSongs =
                        updateTableContent(
                            BaseAppFrame.generalSongList,
                            BaseAppFrame.stationsList,
                            PlayListCreator.stationOptions,
                            PlayListCreator.generalSongsList);
                }
            }
        });
        add(createPlayListButton);

        seeStationListButton.setText("Lista de Emisoras");
        seeStationListButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        seeStationListButton.setBounds(220, 170, 170, 50);
        seeStationListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(3);
            }
        });
        add(seeStationListButton);

        createProgramButton.setText(StringUtils
                .encodeStringUTF8("Crear Programación"));
        createProgramButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createProgramButton.setBounds(40, 170, 170, 50);
        createProgramButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(4);
                if(!BaseAppFrame.stationsList.isEmpty()) {
                    updateComboBoxElementList(
                            BaseAppFrame.stationsList,
                            ProgramCreator.stationSelector);
                    updateTableContent(
                            BaseAppFrame.stationsList.get(
                                ProgramCreator.stationSelector.getSelectedIndex()
                            ),
                            ProgramCreator.stationSelector,
                            ProgramCreator.playListsList);
                }
            }
        });
        add(createProgramButton);

        playProgramButton.setText(StringUtils
                .encodeStringUTF8("Reproducir Programación"));
        playProgramButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playProgramButton.setBounds(120, 230, 200, 50);
        playProgramButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(5);
            }
        });
        add(playProgramButton);
    }
}
