/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.Persistence;

/**
 *
 * @author drm511
 */
public class SaveGameDescription {

    public final String username;
    public final String timestamp;
    public final String path;

    public SaveGameDescription(String username, String timestamp, String path) {
        this.username = username;
        this.timestamp = timestamp;
        this.path = path;
    }
}
