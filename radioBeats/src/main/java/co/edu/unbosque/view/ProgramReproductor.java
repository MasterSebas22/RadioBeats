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

public class ProgramReproductor extends JPanel {

    private JButton backbutton;
    private JScrollPane jScrollPane1;
    private JButton playButton;
    private JLabel playProgramTittleLabel;
    private JTable programsList;
    private JButton stopButton;
   //

    public ProgramReproductor() {
        initComponents();
    }

    private void initComponents() {
        playProgramTittleLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        programsList = new JTable();
        playButton = new JButton();
        stopButton = new JButton();
        backbutton = new JButton();

        setLayout(null);
        setSize(new Dimension(515, 542)); //Frame size 515x442
        setPreferredSize(new Dimension(515, 542));

        playProgramTittleLabel.setFont(new Font("sansserif", 0, 24));
        playProgramTittleLabel.setText("Reproducir Programacion");
        playProgramTittleLabel.setBounds(105, 20, 310, 30);
        add(playProgramTittleLabel);

        programsList.setModel(new DefaultTableModel(
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
        jScrollPane1.setBounds(30, 80, 450, 350);
        jScrollPane1.setViewportView(programsList);
        add(jScrollPane1);

        playButton.setText("Reproducir");
        playButton.setBounds(75, 460, 100, 30);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(playButton);

        stopButton.setText("Detener");
        stopButton.setBounds(205, 460, 100, 30);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(stopButton);

        backbutton.setText("Atras");
        backbutton.setBounds(335, 460, 100, 30);
        backbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(backbutton);
    }
}
