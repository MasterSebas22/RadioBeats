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
public class ProgramCreator extends JPanel {

    private JLabel createProgramTittleLabel;
    private JLabel stationSelectorLabel;
    private JLabel playListsListLabel;
    private JComboBox<String> stationSelector;
    private JScrollPane playListListScrollView;
    private JTable playListsList;
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
        createProgramTittleLabel.setText(StringEncoder
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
                    new String[] { "Emisora 1", "Emisora 2", "Emisora 3",
                        "Emisora 4" }
                    ));
        stationSelector.setBounds(140, 96, 240, 30);
        stationSelector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(stationSelector);

        playListsList.getTableHeader().setEnabled(false);
        playListsList.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
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
        playListListScrollView.setBounds(34, 171, 450, 240);
        playListListScrollView.setViewportView(playListsList);
        add(playListListScrollView);

        acceptButton.setText("Aceptar");
        acceptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        acceptButton.setBounds(144, 430, 100, 30);
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
                //TODO: Implement the other part
            }
        });
        add(acceptButton);

        backButton.setText(StringEncoder.encodeStringUTF8("Atrás"));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBounds(280, 430, 100, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
            }
        });
        add(backButton);
    }
}
