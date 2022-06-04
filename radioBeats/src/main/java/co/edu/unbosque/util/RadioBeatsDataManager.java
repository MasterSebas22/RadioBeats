package co.edu.unbosque.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.unbosque.Main;
import co.edu.unbosque.model.Song;
import co.edu.unbosque.model.dto.StationDTO;

/**
 *
 * @author Bryan Baron
 *
 * @version 1.0
 *
 */
public class RadioBeatsDataManager {

    public static ObjectMapper mapper = new ObjectMapper();
    public static final String TMP_MAIN_DIR_NAME = "radioBeatsSavedData";
    public static final String TMP_SONGS_DIR_NAME = "importedSongs";
    public static final String TMP_STATIONS_DIR_NAME = "stationsCreated";

    /**
     * Obtains the general aplication's context path
     *
     * @return the general aplication's context path
     */
    public static String getAplicationContextPath() {
        String absPath = null;
        try {
            absPath = Main.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath();
            absPath = absPath.substring(0, absPath.lastIndexOf("/"));
            absPath = absPath.trim();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return absPath;
    }

    /**
     * Creates the main data target directory and its subdirectories
     *
     * @return returns the main target directory absolute path
     */
    public static String getOrCreateDataTargetDirectories() {
        String cwd = getAplicationContextPath();
        String mainTargetDirPath = String.format("%s/.%s", cwd,
                TMP_MAIN_DIR_NAME);

        File targetDir = new File(mainTargetDirPath);
        if(!targetDir.exists()) {
            targetDir.mkdir();

            for(int i = 0; i < 2; i++) {
                File subDir = new File(String.format("%s/%s",
                            mainTargetDirPath, i == 0
                                ? TMP_SONGS_DIR_NAME
                                : TMP_STATIONS_DIR_NAME));
                if(!subDir.exists()) subDir.mkdir();
            }
        }

        return mainTargetDirPath;
    }

    /**
     * Checks if a path-specified data unit exist
     *
     * @param dataUnitPath data unit absolute path
     * @return the data unit existence corroboration result
     */
    public static boolean dataUnitExists(String dataUnitPath) {
        File jsonFile = new File(dataUnitPath);
        return jsonFile.exists();
    }

    /**
     * Serializes a specified POJO into a Json object
     *
     * @param directoryOption directory option where the creating Json object
     * will be stored
     * @param name desired name for the creating Json object
     * @param object desired POJO to get it maped into a Json object
     */
    public static void createDataUnit(int directoryOption, String name,
            Object object) {
        String mainTargetDirPath = getOrCreateDataTargetDirectories();
        String selectedDir = directoryOption == 1
            ? TMP_SONGS_DIR_NAME
            : TMP_STATIONS_DIR_NAME;
        String newDataUnitPath =
            String.format("%s/%s/%s.json",mainTargetDirPath,
                    selectedDir, name);

        if(object instanceof Song)
            ((Song) object).setDataUnitPath(newDataUnitPath);
        else if (object instanceof StationDTO)
            ((StationDTO) object).setDataUnitPath(newDataUnitPath);

        try {
            mapper.writeValue(new File(newDataUnitPath), object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a path-specified dataUnit
     *
     * @param dataUnitPath absolute path of the deleting data unit
     */
    public static void deleteDataUnit(String dataUnitPath) {
        File deletingDataUnit = new File(dataUnitPath);
        if(!deletingDataUnit.exists()) return;
        deletingDataUnit.delete();
    }

    /**
     * Deserializes an existent unit or group of data units Json objects
     * into a type-specified POJOs
     *
     * @param <T> type of the deserialized POJO
     * @param type type of the POJO to get it maped from the existent Json object
     * @return a list with the deserialized game doto units Json objects retrived
     */
    public static <T> ArrayList<T> retriveDataUnitsAsObjects(Class<T> type) {
       String selectedDir = type.equals(Song.class)
            ? TMP_SONGS_DIR_NAME
            : TMP_STATIONS_DIR_NAME;

        File objectsTargetDir = new File(
                String.format("%s/%s", getOrCreateDataTargetDirectories(),
                    selectedDir));
        File[] listOfObjects = objectsTargetDir.listFiles();
        ArrayList<T> retrivedObjects = new ArrayList<>();

        try {
            for(File unitDataSaved: listOfObjects) {
                retrivedObjects.add(mapper.readValue(unitDataSaved, type));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retrivedObjects;
    }
}
