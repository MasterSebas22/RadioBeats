package co.edu.unbosque.view;

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

        setLayout(null);
        setSize(new Dimension(515, 555)); //Frame size 515x555
        setPreferredSize(new Dimension(515, 555));

        createProgramTittleLabel.setFont(new Font("sansserif", 0, 24));
        createProgramTittleLabel.setText("Crear Programacion");
        createProgramTittleLabel.setBounds(140, 20, 250, 29);
        add(createProgramTittleLabel);

        stationSelectorLabel.setText("Emisora");
        stationSelectorLabel.setBounds(228, 74, 60, 17);
        add(stationSelectorLabel);

        playListsListLabel.setText("Lista de PlayLists");
        playListsListLabel.setBounds(200, 150, 120, 17);
        add(playListsListLabel);

        playListsList.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        playListListScrollView.setBounds(30, 180, 450, 280);
        playListListScrollView.setViewportView(playListsList);
        add(playListListScrollView);

        stationSelector.setModel(new DefaultComboBoxModel<>(
                    new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }
                    ));
        stationSelector.setBounds(140, 100, 240, 30);
        stationSelector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(stationSelector);

        acceptButton.setText("Aceptar");
        acceptButton.setBounds(200, 480, 100, 30);
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(acceptButton);
    }
}
