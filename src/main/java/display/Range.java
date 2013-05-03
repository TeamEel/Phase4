/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

/**
 *
 * @author James
 */
public class Range {
    public static String[] Formatted(String path, int from, int to) throws IllegalArgumentException
    {
        if(from>to){
            throw new IllegalArgumentException("To cannot be less than from");
        }
        String[] paths = new String[1+to-from];
        
        for(int i = 0;from+i<=to;i++) { 
            paths[i] = String.format(path, from+i);
        }
         return paths;
    }
}
