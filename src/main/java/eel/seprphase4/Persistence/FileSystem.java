package eel.seprphase4.Persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Filesystem interaction
 *
 * Could be a singleton (only one filesystem) but static methods for convenience.
 *
 * @author David
 */
public class FileSystem {

    /*
     * Returns the folder on disk in which game files will be saved to 
     * @return Absolute path to folder with trailing slash
     */
    public static String savePath() {
        return System.getProperty("user.home") +
               System.getProperty("file.separator") +
               "sepr.teameel.gamesaves" +
               System.getProperty("file.separator");
    }

    /**
     * Lists all files stored under a player's username in the default save path
     *
     * @param username The Player's username
     *
     * @return A list of file names (not paths) in the directory that match the username
     */
    public static String[] listSaveGames(String username) {
        File saveDir = new File(savePath());
        File[] allFiles = saveDir.listFiles();
        ArrayList<String> acceptableSaveGameFiles = new ArrayList<String>();
        for (File file : allFiles) {
            if (file.isFile()) {
                //sepr.teameel. = 13 chars long
                //if(file.getName().toLowerCase().endsWith(".nuke") && file.getName().startsWith(userName, 13))
                if (file.getName().matches("sepr.teameel." + username + ".([0-9]+).nuke")) {
                    acceptableSaveGameFiles.add(file.getName());
                }

            }
        }
        return acceptableSaveGameFiles.toArray(new String[acceptableSaveGameFiles.size()]);
    }

    public static SaveGameDescription[] listSaveGames() {
        File saveDir = new File(savePath());
        File[] allFiles = saveDir.listFiles();
        ArrayList<SaveGameDescription> saveGames = new ArrayList<SaveGameDescription>();
        for (File f : allFiles) {
            if (f.isFile()) {
                Pattern p = Pattern.compile("sepr.teameel.([A-Za-z0-9 ]+).([0-9]+).nuke");
                Matcher m = p.matcher(f.getName());
                if (m.matches()) {
                    String username, timestamp, path;
                    path = f.toString();
                    username = m.group(1);
                    timestamp = new Date(Long.parseLong(m.group(2))).toString();
                    saveGames.add(new SaveGameDescription(username, timestamp, path));
                }
            }
        }
        Collections.reverse(saveGames);
        return saveGames.toArray(new SaveGameDescription[0]);
    }

    /**
     * Make all directories in the savePath
     *
     * @throws IOException Error in creating the directories
     */
    public static void createSavePath() throws IOException {
        if (new File(savePath()).mkdirs()) {
            throw new IOException("Could not create full save path");
        }
    }

    public static void writeString(String fileName, String contents) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(savePath() + fileName);
        out.print(contents);
        out.close();
    }

    public static String readString(String fileName) throws IOException {
        return Utils.readFile(savePath() + fileName);
    }
}
