package co.edu.unbosque.util;

import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;

/**
 *
 * @author Bryan Baron
 *
 * @version 1.0
 *
 */
public class StringEncoder {

    /**
     * Encode the given string's special characters into the UTF8 standard
     *
     * @param text desired string to encode its special characters
     * @return the encoded version of the original string specified
     */
    public static String encodeStringUTF8(String text) {
        String encodedString = null;

        try {
            encodedString = new String(text.getBytes(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            encodedString = text;
            JOptionPane.showMessageDialog(null,
                    "Error de procesamiento de texto. " +
                    "Retornando el texto original.",
                    "Reporte de error", JOptionPane.PLAIN_MESSAGE);
        }

        return  encodedString;
    }
}
