package co.edu.unbosque.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;

import co.edu.unbosque.model.Song;
import co.edu.unbosque.model.dto.StationDTO;
import co.edu.unbosque.util.RadioBeatsDataManager;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseAppFrame extends JFrame {

    private static BaseAppFrame baseAppFrame;
    static {
        setFrameLookAndFeel();
        baseAppFrame = new BaseAppFrame();
    }
    private GroupLayout baseFrameLayout;
    private JButton closeAppOption;
    private JButton importSoundOption;
    private static JMenuBar baseFrameMenuBar;
    private static JPanel baseAppFramePanel;
    private static MainMenu mainMenu;
    private static StationManager stationManager;
    private static PlayListCreator playListCreator;
    private static StationList stationList;
    private static ProgramCreator programCreator;
    private static ProgramPlayer programReproductor;
    private static SongImporter songImporter;
    private static FileChooser fileChooser;
    protected static List<Song> generalSongList;
    protected static List<StationDTO> stationsList;


    /**
     * Creates new form BaseAppFrame
     */
    private BaseAppFrame() {
        initComponents();
    }

    /**
     * Initalizes the jFrame components
     */
    private void initComponents() {
        baseFrameLayout = new GroupLayout(getContentPane());
        baseFrameMenuBar = new JMenuBar();
        importSoundOption = new JButton();
        closeAppOption = new JButton();
        baseAppFramePanel = null;
        mainMenu = new MainMenu();
        stationManager = new StationManager();
        playListCreator = new PlayListCreator();
        stationList = new StationList();
        programCreator = new ProgramCreator();
        programReproductor = new ProgramPlayer();
        songImporter = new SongImporter();
        fileChooser = new FileChooser();
        generalSongList = null;
        stationsList = null;

        setTitle("RadioBeats");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(mainMenu.getSize());
        setPreferredSize(mainMenu.getSize());

        importSoundOption.setText("Importar");
        importSoundOption.setCursor(new Cursor(Cursor.HAND_CURSOR));
        importSoundOption.setFocusPainted(false);
        importSoundOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                reloadFrameContent(6);
            }
        });
        baseFrameMenuBar.add(importSoundOption);

        closeAppOption.setText("Salir");
        closeAppOption.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeAppOption.setFocusPainted(false);
        closeAppOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
        baseFrameMenuBar.add(closeAppOption);

        setJMenuBar(baseFrameMenuBar);

        baseAppFramePanel = mainMenu;

        generalSongList = RadioBeatsDataManager
            .retriveDataUnitsAsObjects(Song.class);
        if(!generalSongList.isEmpty()) {
            for(int i = 0; i < generalSongList.size(); i++) {
                File file = new File(generalSongList.get(i).getSongPath());
                if(!file.exists()) {
                    RadioBeatsDataManager.deleteDataUnit(
                            generalSongList.get(i).getDataUnitPath()
                    );
                    generalSongList.remove(i);
                }
            }
        }
        stationsList = RadioBeatsDataManager
            .retriveDataUnitsAsObjects(StationDTO.class);

        getContentPane().setLayout(baseFrameLayout);
        baseFrameLayout.setHorizontalGroup(
            baseFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(baseAppFramePanel, GroupLayout.DEFAULT_SIZE,
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        baseFrameLayout.setVerticalGroup(
            baseFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(baseAppFramePanel, GroupLayout.DEFAULT_SIZE,
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Retrives the singleton BaseAppFrame instance
     *
     * @return the BaseAppFrame singleton instance
     */
    public static BaseAppFrame getSingletonInstance() {
        return baseAppFrame;
    }

    /**
     * Reloads the whole frame's components and general look
     *
     * @param panelOption the replacing JFrame panel option
     */
    public static void reloadFrameContent(int panelOption) {
        baseAppFrame.remove(baseAppFramePanel);

        switch(panelOption) {
            case 1:
                baseAppFramePanel = stationManager;
                break;
            case 2:
                baseAppFramePanel = playListCreator;
                break;
            case 3:
                baseAppFramePanel = stationList;
                break;
            case 4:
                baseAppFramePanel = programCreator;
                break;
            case 5:
                baseAppFramePanel = programReproductor;
                break;
            case 6:
                baseAppFramePanel = songImporter;
                break;
            case 7:
                baseAppFramePanel = fileChooser;
                break;
            default:
                baseAppFramePanel = mainMenu;
                baseAppFramePanel.setSize(new Dimension(430, 340));
                break;
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                baseAppFrame.add(baseAppFramePanel);
                baseAppFrame.setSize(baseAppFramePanel.getSize());
                baseAppFrame.setPreferredSize(baseAppFramePanel.getSize());
                baseAppFrame.repaint();
                baseAppFrame.setLocationRelativeTo(null);
                baseFrameMenuBar.getComponent(0).setEnabled(baseAppFramePanel
                        .equals(mainMenu) ? true : false);
            }
        });
    }

    /**
     * Loads and sets the global JFrame look and feel
     */
    private static void setFrameLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseAppFrame.class.getName())
                .log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BaseAppFrame.class.getName())
                .log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BaseAppFrame.class.getName())
                .log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(BaseAppFrame.class.getName())
                .log(Level.SEVERE, null, ex);
        }
    }
}
