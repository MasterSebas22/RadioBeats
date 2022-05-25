package co.edu.unbosque.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
public class PlayListCreator extends JPanel {

    private JLabel playListCreatorTittleLabel;
    private JLabel playListNameLabel;
    private JLabel stationSelectorLabel;
    private JLabel generalSongsListLabel;
    private JTextField playListNameField;
    private JComboBox<String> stationOptions;
    private JTable soundsList;
    private JScrollPane soundListScrollView;
    private JButton acceptButton;
    private JButton backButton;
    private String playListName;
    // private Object[][] soundList;

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
        soundListScrollView = new JScrollPane();
        soundsList = new JTable();
        acceptButton = new JButton();
        backButton = new JButton();

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
        playListNameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });

        add(playListNameField);

        stationOptions.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stationOptions.setBounds(278, 90, 180, 30);
        stationOptions.setModel(new DefaultComboBoxModel<>(
                    new String[] { "Emisora 1", "Emisora 2", "Emisora 3",
                        "Emisora 4" }
                    ));
        stationOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(stationOptions);

        soundsList.getTableHeader().setEnabled(false);
        soundsList.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "#------>*", "Nombre", "Artista",
                StringEncoder.encodeStringUTF8("Genéro")
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
        soundsList.getColumnModel().getColumn(0).setPreferredWidth(1);
        soundListScrollView.setCursor(new Cursor(Cursor.HAND_CURSOR));
        soundListScrollView.setBounds(30, 155, 460, 220);
        soundListScrollView.setViewportView(soundsList);
        add(soundListScrollView);

        acceptButton.setText("Aceptar");
        acceptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        acceptButton.setBounds(140, 390, 100, 30);
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
                //TODO: Implement the other part
            }
        });
        add(acceptButton);

        backButton.setText(StringEncoder.encodeStringUTF8("Atrás"));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBounds(280, 390, 100, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
            }
        });
        add(backButton);
    }
}
