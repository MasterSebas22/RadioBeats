package co.edu.unbosque.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.unbosque.Main;
import co.edu.unbosque.model.dao.PlayListDao;
import co.edu.unbosque.model.dao.SongDao;

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
    public static final String TMP_PLAYLISTS_DIR_NAME = "playListCreated";
    public static final String TMP_PROGRAMS_DIR_NAME = "programsCreated";

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
        String targetDirsPath = String.format("%s/{%s,%s,%s}", cwd,
                TMP_SONGS_DIR_NAME, TMP_PLAYLISTS_DIR_NAME,
                TMP_PROGRAMS_DIR_NAME);

        File targetDirs = new File(targetDirsPath);
        if(!targetDirs.exists()) targetDirs.mkdirs();

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
            : directoryOption == 2
            ? TMP_PLAYLISTS_DIR_NAME
            : TMP_PROGRAMS_DIR_NAME;

        try {
            mapper.writeValue(new File(String.format("%s/%s.json",
                            String.format("%s/%s", mainTargetDirPath, selectedDir),
                            name)), object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes an existent unit or group of data units Json objects
     * into a type-specified POJOs
     *
     * @param type type of the POJO to get it maped from the existent Json object
     * @return a list with the deserialized game doto units Json objects retrived
     */
    public static <T> ArrayList<T> retriveDataUnitsAsObjects(Class<T> type) {
        String selectedDir = type == SongDao.class
            ? TMP_SONGS_DIR_NAME
            : type == PlayListDao.class
            ? TMP_PLAYLISTS_DIR_NAME
            : TMP_PROGRAMS_DIR_NAME;

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

    /**
     * Loads the existent data units saved earlier
     *
     * @return a list with the history of the earlier game data units saved earlier
     */
    // public static <T> ArrayList<T> loadDataUnits(Class<T> type) {
    //     File dataSavedDirectory = new File(String.format("%s/.%s",
    //                 getAplicationContextPath(), TMP_MAIN_DIR_NAME));
    //     ArrayList<GameRegister> gamesHistory = null;
    //
    //     if(dataSavedDirectory.exists()) {
    //         if(dataSavedDirectory.listFiles().length > 0) {
    //             gamesHistory = retriveGameDataUnitAsObject(GameRegister.class);
    //             gamesHistory.sort(
    //                     (a, b) -> String.valueOf(a.getGameRegisterId())
    //                                 .compareTo(String.valueOf(b.getGameRegisterId()))
    //                     );
    //             GameRegister.setGameRegisterIdRef(
    //                     gamesHistory.get(gamesHistory.size() - 1)
    //                     .getGameRegisterId());
    //         } else
    //             gamesHistory = new ArrayList<>();
    //     } else
    //         gamesHistory = new ArrayList<>();
    //
    //     return gamesHistory;
    // }
}
