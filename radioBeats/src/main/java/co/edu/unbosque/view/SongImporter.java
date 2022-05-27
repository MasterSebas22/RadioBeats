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
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import co.edu.unbosque.util.GraphicalComponentsTools;
import co.edu.unbosque.util.StringEncoder;
import lombok.Getter;
import lombok.Setter;

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
public class SongImporter extends JPanel
    implements GraphicalComponentsTools {

    private JLabel songImporterTittleLabel;
    private JLabel songNameLabel;
    private JLabel artistNameLabel;
    private JLabel songGenLabel;
    @Setter @Getter
    private static JTextField songNameField;
    @Setter @Getter
    private static JTextField artistNameField;
    private JComboBox<String> songGenOptions;
    private JButton selectFileButton;
    @Setter @Getter
    private static JButton acceptButton;
    private JButton cancelButton;

    /**
     * Creates new form SongImporter
     */
    public SongImporter() {
        initComponents();
    }

    /**
     * Initalizes the jFrame components
     */
    private void initComponents() {
        songImporterTittleLabel = new JLabel();
        songNameLabel = new JLabel();
        artistNameLabel = new JLabel();
        songGenLabel = new JLabel();
        songNameField = new JTextField();
        artistNameField = new JTextField();
        songGenOptions = new JComboBox<>();
        selectFileButton = new JButton();
        acceptButton = new JButton();
        cancelButton = new JButton();

        setLayout(null);
        setSize(new Dimension(420, 296));
        setPreferredSize(new Dimension(420, 296));

        songImporterTittleLabel.setFont(new Font("sansserif", 0, 24));
        songImporterTittleLabel.setBounds(113, 20, 210, 29);
        songImporterTittleLabel.setText(StringEncoder
                .encodeStringUTF8("Importar Canción"));
        add(songImporterTittleLabel);

        songNameLabel.setText("Nombre");
        songNameLabel.setBounds(94, 78, 95, 17);
        add(songNameLabel);

        artistNameLabel.setText("Artista");
        artistNameLabel.setBounds(278, 78, 47, 17);
        add(artistNameLabel);

        songGenLabel.setText(StringEncoder.encodeStringUTF8("Género"));
        songGenLabel.setBounds(95, 135, 47, 17);
        add(songGenLabel);

        songNameField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        songNameField.setBounds(50, 98, 140, 30);
        songNameField.getDocument().addDocumentListener(
                new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                uptadeLocalComponentEnabledState(acceptButton,
                        songNameField, artistNameField,
                        BaseAppFrame.getFileSelectedPath());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                uptadeLocalComponentEnabledState(acceptButton,
                        songNameField, artistNameField,
                        BaseAppFrame.getFileSelectedPath());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        add(songNameField);

        artistNameField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        artistNameField.setBounds(230, 98, 140, 30);
        artistNameField.getDocument().addDocumentListener(
                new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                uptadeLocalComponentEnabledState(acceptButton,
                        songNameField, artistNameField,
                        BaseAppFrame.getFileSelectedPath());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                uptadeLocalComponentEnabledState(acceptButton,
                        songNameField, artistNameField,
                        BaseAppFrame.getFileSelectedPath());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        add(artistNameField);

        songGenOptions.setCursor(new Cursor(Cursor.HAND_CURSOR));
        songGenOptions.setBounds(50, 154, 140, 30);
        songGenOptions.setModel(new DefaultComboBoxModel<>(
                    new String[] {
                        "Rock/Metal", "HipHop/Rap", "Pop", "Reaggeton",
                        "Vallenato", "Reagge", "Salsa/Merengue", "Otro" }
                    ));
        songGenOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //
            }
        });
        add(songGenOptions);

        selectFileButton.setText("Tomar Fichero");
        selectFileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectFileButton.setBounds(230, 145, 140, 40);
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(7);
                if(BaseAppFrame.getFileSelectedPath() != null) {
                }
                //TODO: Implement the other part
            }
        });
        add(selectFileButton);

        acceptButton.setText("Aceptar");
        acceptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        acceptButton.setBounds(92, 213, 100, 30);
        acceptButton.setEnabled(false);
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
                //TODO: Implement the other part
            }
        });
        add(acceptButton);

        cancelButton.setText("Cancelar");
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.setBounds(228, 213, 100, 30);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BaseAppFrame.reloadFrameContent(-1);
            }
        });
        add(cancelButton);
    }
}
