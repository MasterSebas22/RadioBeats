package co.edu.unbosque.util;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author Bryan Baron
 *
 * @version 1.0
 *
 */
public class StringEncoder {

    /**
     *
     */
    public static String encodeUTF8(String text) {
        String encodedString = null;

        try {
            encodedString = new String(text.getBytes(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            // CustomDialog alert = new CustomDialog(null,
            //         "[Error]: ¡Codificación de carácteres no soportada!", 0);
            // alert.setVisible(true);
        }

        return  encodedString;
    }
}
