package co.edu.unbosque.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel {

    private JLabel mainMenuTitleLabel;
    private JButton createStationButton;
    private JButton createPlayListButton;
    private JButton seeStationListButton;
    private JButton createProgramButton;
    private JButton playProgramButton;

    public MainMenu() {
        initComponents();
    }

    private void initComponents() {
        mainMenuTitleLabel = new JLabel();
        createStationButton = new JButton();
        createPlayListButton = new JButton();
        seeStationListButton = new JButton();
        createProgramButton = new JButton();
        playProgramButton = new JButton();

        setLayout(null);
        setSize(new Dimension(430, 340)); //Frame size 430x340
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
                //
            }
        });
        add(createStationButton);

        createPlayListButton.setText("Crear PlayList");
        createPlayListButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createPlayListButton.setBounds(220, 110, 170, 50);
        createPlayListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(createPlayListButton);

        seeStationListButton.setText("Lista de Emisoras");
        seeStationListButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        seeStationListButton.setBounds(40, 170, 170, 50);
        seeStationListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(seeStationListButton);

        createProgramButton.setText("Crear Programacion");
        createProgramButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createProgramButton.setBounds(220, 170, 170, 50);
        createProgramButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(createProgramButton);

        playProgramButton.setText("Reproducir Programacion");
        playProgramButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playProgramButton.setBounds(120, 230, 200, 50);
        playProgramButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(playProgramButton);
    }
}
