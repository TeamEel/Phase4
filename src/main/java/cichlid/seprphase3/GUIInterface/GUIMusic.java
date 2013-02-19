/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cichlid.seprphase3.GUIInterface;

import java.io.*;
import sun.audio.*;


/**
 *
 * @author Tomasz
 */

public class GUIMusic {

InputStream in;    
AudioStream StreamIn;    
    
    GUIMusic(String filename){
        try{
            in = new FileInputStream(filename);
            StreamIn = new AudioStream(in);    
        }
        catch(Exception e){
            System.out.println("Error: " + e.toString());
        }
    }
    
    void Play(){
       AudioPlayer.player.start(StreamIn);
    }
    
    void Stop(){
       AudioPlayer.player.stop(StreamIn); 
    }
}
