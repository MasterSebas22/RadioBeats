package co.edu.unbosque.view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;

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
public class BaseAppFrame extends javax.swing.JFrame {

    private static BaseAppFrame baseAppFrame;
    static {
        setFrameLookAndFeel();
        baseAppFrame = new BaseAppFrame();
    }
    private GroupLayout baseFrameLayout;
    private JMenuBar baseFrameMenuBar;
    private JButton closeAppOption;
    private JButton importSoundOption;
    private static JPanel baseAppFramePanel;
    private static MainMenu mainMenu;
    private static StationCreator stationCreator;
    private static PlayListCreator playListCreator;
    private static StationList stationList;
    private static ProgramCreator programCreator;
    private static ProgramReproductor programReproductor;
    private static int startupConfirmation;

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
        stationCreator = new StationCreator();
        playListCreator = new PlayListCreator();
        stationList = new StationList();
        programCreator = new ProgramCreator();
        programReproductor = new ProgramReproductor();
        startupConfirmation = 0;

        setTitle("RadioBeats");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setPreferredSize(mainMenu.getSize());

        importSoundOption.setText("Importar");
        importSoundOption.setCursor(new Cursor(Cursor.HAND_CURSOR));
        importSoundOption.setFocusPainted(false);
        importSoundOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //TODO: MAKE THE IMPORT FRAME AND CALL HERE
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

        if(startupConfirmation == 0) baseAppFramePanel = mainMenu;

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
        startupConfirmation = startupConfirmation == 0 ? 1 : 1;
        baseAppFrame.remove(baseAppFramePanel);

        switch(panelOption) {
            case 1:
                baseAppFramePanel = stationCreator;
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
            default:
                baseAppFramePanel = mainMenu;
                break;
        }

        baseAppFrame.add(baseAppFramePanel);
        baseAppFrame.setSize(baseAppFramePanel.getSize());
        baseAppFrame.setPreferredSize(baseAppFramePanel.getSize());
        baseAppFrame.repaint();
        baseAppFrame.setLocationRelativeTo(null);
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
