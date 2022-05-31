package co.edu.unbosque.util;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.PlayList;
import co.edu.unbosque.model.Song;
import co.edu.unbosque.model.dao.StationDao;

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
public interface GraphicalComponentsTools {

    /**
     * Updates the desired component's enabled state based on a
     * JTable's column-row selection
     *
     * @param component desired component reference to get updated
     * @param table basing JTable to make the update
     */
    default <T extends JComponent, V extends JTable>
        void uptadeLocalComponentEnabledState(T component, V table) {
            int fieldSelectionConfirm = 0;

            for(int i = 0; i < table.getRowCount(); i++) {
                if(!(table.getValueAt(i, 0) == null ||
                        (Boolean) table.getValueAt(i, 0) == false))
                    ++fieldSelectionConfirm;
            }

            component.setEnabled(fieldSelectionConfirm != 0
                    ? true
                    : false);
    }

    /**
     * Updates the desired component's enabled state based on a JTextField's
     * content
     *
     * @param component desired component reference to get updated
     * @param textField basing JTextField to make the update
     */
    default <T extends JComponent, V extends JTextField>
        void uptadeLocalComponentEnabledState(T component, V textField) {
        component.setEnabled(!textField.getText().equals("")
            ? true
            : false);
    }

    /**
     * Updates the desired component's enabled state based on a JTextField's
     * content and a JTable's column-row selection
     *
     * @param component desired component reference to get updated
     * @param textField basing JTextField to make the update
     * @param table basing JTable to make the update
     */
    default <T extends JComponent, U extends JTextField, V extends JTable>
        void uptadeLocalComponentEnabledState(T component, U textField,
                V table) {
            int fieldSelectionConfirm = 0;
            boolean isTextFieldFilled = textField.getText().equals("")
            ? false
            : true;

            for(int i = 0; i < table.getRowCount(); i++) {
                if(!(table.getValueAt(i, 0) == null ||
                        (Boolean) table.getValueAt(i, 0) == false))
                    ++fieldSelectionConfirm;
            }

            component.setEnabled(
                    (fieldSelectionConfirm != 0) && isTextFieldFilled
                    ? true
                    : false);
    }

    /**
     * Updates the desired component's enabled state based on a couple
     * of JTextFields' and a String contents
     *
     * @param component desired component reference to get updated
     * @param textField1 first basing JTextField to make the update
     * @param textField2 second basing JTextField to make the update
     * @param text basing String to make the update
     */
    default <T extends JComponent, U extends JTextField, V extends JTextField>
        void uptadeLocalComponentEnabledState(T component, U textField1,
                V textField2, String text) {
            boolean isTextField1Filled = textField1.getText().equals("")
            ? false
            : true;
            boolean isTextField2Filled = textField2.getText().equals("")
            ? false
            : true;

            component.setEnabled(
                    isTextField1Filled && isTextField2Filled && !(text == null)
                    ? true
                    : false);
    }

    /**
     * Updates a JComboBox's elements list based on a List content
     *
     * @param list basing list to make the JComboBox's elements list update
     * @param comboBox JComboBox reference to get updated
     */
    default <T extends StationDao, U extends JComboBox<Object>>
        void updateComboBoxElementList(List<T> list, U comboBox) {
        String[] elementNames = new String[list.size()];
        for(int i = 0; i < list.size(); i++) {
            elementNames[i] = list.get(i).getStationName();
        }
        comboBox.setModel(new DefaultComboBoxModel<>(elementNames));
    }

    /**
     * Updates a JTable's content based on a Station's atributes and a
     * JComboBox selection
     *
     * @param station basing station to make the table content update
     * @param comboBox basing JComboBox to make the table content update
     * @param table JTable to get updated
     */
    default <T extends StationDao, U extends JComboBox<Object>, V extends JTable>
        void updateTableContent(StationDao station, U comboBox, V table) {
        DefaultTableModel tbModel = null;
        Object[] listElementsArray = station.getStationPlayListsList().toArray();

        tbModel = (DefaultTableModel) table.getModel();
        tbModel.setRowCount(0);

        for(int i = 0; i < listElementsArray.length; i++) {
            tbModel.addRow(new Object[] {
                false,
                ((PlayList) listElementsArray[i]).getPlayListName(),
            });
        }

        table.revalidate();
    }

    /**
     * Updates a JTable's content based on a couple of List's contents and
     * a JComboBox element selection
     *
     * @param list1 first basing list to make the table content update
     * @param list2 second basing list to make the table content update
     * @param comboBox basing JComboBox to make the table content update
     * @param table JTable to get updated
     */
    default <T extends Song, U extends StationDao, V extends JComboBox<Object>,
            W extends JTable>
        void updateTableContent(List<T> list1, List<U> list2, V comboBox,
                W table) {
        DefaultTableModel tbModel = null;
        Song[] compatibleElements = new Song[0];
        int compatibleElementsConut = 0;
        int countAux = 0;

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < list1.size(); j++) {
                if(list1.get(j).getSongGender().equals(list2.get(
                                comboBox.getSelectedIndex())
                                    .getStationMusicGender())) {
                    if(i == 0) {
                        ++compatibleElementsConut;
                        if(j == list1.size() - 1) {
                            compatibleElements =
                                new Song[compatibleElementsConut];
                            continue;
                        }
                    } else if(i == 1 && compatibleElements.length > 0) {
                        compatibleElements[countAux++] = list1.get(j);
                    }
                }
            }
        }

        if(compatibleElements.length != 0) {
            tbModel = (DefaultTableModel) table.getModel();
            tbModel.setRowCount(0);

            for(int i = 0; i < compatibleElements.length; i++) {
                tbModel.addRow(new Object[] {
                    false,
                    compatibleElements[i].getSongName(),
                    compatibleElements[i].getSongArtristName(),
                    compatibleElements[i].getSongGender(),
                });
            }

            table.revalidate();
        }
    }
}
