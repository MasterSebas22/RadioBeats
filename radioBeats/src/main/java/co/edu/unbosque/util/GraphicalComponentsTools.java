package co.edu.unbosque.util;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;

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
     * @param component desired component reference to make the update
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
     * @param component desired component reference to make the update
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
     * @param component desired component reference to make the update
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
     * @param component desired component reference to make the update
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
}
