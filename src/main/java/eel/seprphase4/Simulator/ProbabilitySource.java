/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.Simulator;

/**
 *
 * @author James
 */
public interface ProbabilitySource {
    public boolean trueOnceIn(int n);
    public int choiceFromZeroTo(int n);
}

