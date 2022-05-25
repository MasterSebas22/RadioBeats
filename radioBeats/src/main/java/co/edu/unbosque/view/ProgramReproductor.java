package co.edu.unbosque.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
public class ProgramReproductor extends JPanel {

    private JLabel programsListLabel;
    private JButton backButton;
    private JScrollPane programsListScrollView;
    private JButton playButton;
    private JLabel playProgramTittleLabel;
    private JTable programsList;
    private JButton stopButton;

    /**
     * Creates new form ProgramReproductor
     */
    public ProgramReproductor() {
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

        setLayout(null);
        setSize(new Dimension(515, 444));
        setPreferredSize(new Dimension(515, 444));

        playProgramTittleLabel.setFont(new Font("sansserif", 0, 24));
        playProgramTittleLabel.setText(StringEncoder
                .encodeStringUTF8("Reproducir Programación"));
        playProgramTittleLabel.setBounds(113, 20, 310, 30);
        add(playProgramTittleLabel);

        programsListLabel.setText(StringEncoder
                .encodeStringUTF8("Lista de Programaciones"));
        programsListLabel.setBounds(184, 60, 180, 30);
        add(programsListLabel);

        programsList.getTableHeader().setEnabled(false);
        programsList.setModel(new DefaultTableModel(
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
        programsList.getColumnModel().getColumn(0).setPreferredWidth(1);
        programsList.getColumnModel().getColumn(1).setPreferredWidth(300);
        programsListScrollView.setBounds(30, 95, 450, 250);
        programsListScrollView.setViewportView(programsList);
        add(programsListScrollView);

        playButton.setText("Reproducir");
        playButton.setBounds(75, 362, 100, 30);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(playButton);

        stopButton.setText("Detener");
        stopButton.setBounds(205, 362, 100, 30);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(stopButton);

        backButton.setText(StringEncoder.encodeStringUTF8("Atrás"));
        backButton.setBounds(335, 362, 100, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
            }
        });
        add(backButton);
    }
}
