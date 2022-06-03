package co.edu.unbosque.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.PlayList;
import co.edu.unbosque.model.Song;
import co.edu.unbosque.model.dto.StationDTO;

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
    default <T extends StationDTO, U extends JComboBox<Object>>
        void updateComboBoxElementList(List<T> list, U comboBox) {
        String[] elementNames = new String[list.size()];
        for(int i = 0; i < list.size(); i++) {
            elementNames[i] = list.get(i).getStationName();
        }
        comboBox.setModel(new DefaultComboBoxModel<>(elementNames));
    }

    /**
     * Updates a JTable's content based on a objects list
     *
     * @param list basing list to make the table content update
     * @param table JTable to get updated
     * @param updatingOption JTable's list-based updating type option
     * @return returns the elements printed in the JTable's content
     */
    default <T extends StationDTO, U extends JTable>
        List<StationDTO> updateTableContent(List<T> list, U table,
                int updatingOption) {
        DefaultTableModel tbModel = null;
        Object[] listElementsArray = list.toArray();
        StationDTO auxStationReference = null;
        List<StationDTO> validElements = updatingOption == 1
            ? null
            : new ArrayList<>();

        tbModel = (DefaultTableModel) table.getModel();
        tbModel.setRowCount(0);

        for(int i = 0; i < listElementsArray.length; i++) {
            if(updatingOption == 1) {
                tbModel.addRow(new Object[] {
                    ((StationDTO) listElementsArray[i])
                        .getStationName(),
                    ((StationDTO) listElementsArray[i])
                        .getStationTransmitionType(),
                    ((StationDTO) listElementsArray[i])
                        .getStationMusicGender()
                });

            } else if(updatingOption == 2) {
                auxStationReference = (StationDTO) listElementsArray[i];
                if(auxStationReference.getStationProgram() != null){
                    validElements.add(auxStationReference);
                    tbModel.addRow(new Object[] {
                        auxStationReference.getStationName(),
                    });
                }
            }
        }

        table.revalidate();

        return validElements;
    }

    /**
     * Updates a JTable's content based on a Object's atributes and a
     * JComboBox selection
     *
     * @param object basing object to make the table content update
     * @param comboBox basing JComboBox to make the table content update
     * @param table JTable to get updated
     */
    default <T extends StationDTO, U extends JComboBox<Object>, V extends JTable>
        void updateTableContent(StationDTO object, U comboBox, V table) {
        DefaultTableModel tbModel = (DefaultTableModel) table.getModel();
        tbModel.setRowCount(0);

        if(object != null) {
            Object[] listElementsArray = object.getStationPlayListsList()
                .toArray();
            for(int i = 0; i < listElementsArray.length; i++) {
                tbModel.addRow(new Object[] {
                    false,
                    ((PlayList) listElementsArray[i]).getPlayListName(),
                });
            }

            if(object.getStationProgram() != null) table.setEnabled(false);
            else table.setEnabled(true);
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
     * @return the elements putted into the table content
     */
    default <T extends Song, U extends StationDTO, V extends JComboBox<Object>,
            W extends JTable>
        Song[] updateTableContent(List<T> list1, List<U> list2, V comboBox,
                W table) {
        DefaultTableModel tbModel = (DefaultTableModel) table.getModel();
        Song[] compatibleElements = null;
        int compatibleElementsConut = 0;
        int countAux = 0;

        if(!list1.isEmpty() && !list2.isEmpty()) {
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < list1.size(); j++) {
                    if(list1.get(j).getSongGender().equals(list2.get(
                                    comboBox.getSelectedIndex())
                                        .getStationMusicGender())) {
                        if(i == 0) {
                            ++compatibleElementsConut;
                        } else {
                            if(compatibleElements == null) {
                                compatibleElements =
                                    new Song[compatibleElementsConut];
                            }
                            compatibleElements[countAux++] = list1.get(j);
                        }
                    }
                }
            }
        }

        tbModel.setRowCount(0);
        if(compatibleElements != null) {
            for(int i = 0; i < compatibleElements.length; i++) {
                tbModel.addRow(new Object[] {
                    false,
                    compatibleElements[i].getSongName(),
                    compatibleElements[i].getSongArtristName(),
                    compatibleElements[i].getSongGender(),
                });
            }
        }
        table.revalidate();

        return compatibleElements;
    }

    /**
     * Updates a local panel's components on exit
     */
    default void updateLocalComponetsOnExit() {}
}
