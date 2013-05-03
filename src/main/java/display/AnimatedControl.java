/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.event.ActionListener;



/**
 *
 * @author James
 */
public interface AnimatedControl extends Control {
    public void start();
    public void stop();
    public void reverse(int ms);
    public void addActionListener(ActionListener al);
}
