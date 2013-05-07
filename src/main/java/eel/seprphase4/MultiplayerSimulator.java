/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4;

import eel.seprphase4.Simulator.Simulator;

/**
 *
 * @author drm511
 */
public class MultiplayerSimulator extends Simulator {

    private final String player1Name;
    private final String player2Name;
    private double condenserAttackProgress;
    private double turbineAttackProgress;
    private double pump1AttackProgress;
    private double pump2AttackProgress;
    private double attackIncrement;
    private double decayDecrement;

    public MultiplayerSimulator(String player1Name, String player2Name) {
        super(player1Name);
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        reset();
        this.attackIncrement = 0.5;
        this.decayDecrement = 0.01;
    }

    public double condenserAttackProgress() {
        return condenserAttackProgress;
    }

    public double turbineAttackProgress() {
        return turbineAttackProgress;
    }

    public double pump1AttackProgress() {
        return pump1AttackProgress;
    }

    public double pump2AttackProgress() {
        return pump2AttackProgress;
    }

    public void attackCondenser() {
        condenserAttackProgress += attackIncrement;
        if (condenserAttackProgress > 1) {
            failCondenser();
            attackSucceeded();
        }
    }

    public void attackTurbine() {
        turbineAttackProgress += attackIncrement;
        if (turbineAttackProgress > 1) {
            failTurbine();
            attackSucceeded();
        }
    }

    public void attackPump1() {
        pump1AttackProgress += attackIncrement;
        if (pump1AttackProgress > 1) {
            failPump(1);
            attackSucceeded();
        }
    }

    public void attackPump2() {
        pump2AttackProgress += attackIncrement;
        if (pump2AttackProgress > 1) {
            failPump(2);
            attackSucceeded();
        }
    }
    
    @Override
    public void step(int steps) throws GameOverException {
        super.step(steps);
        decay(steps);
    }
    
    private void attackSucceeded() {
        reset();
        attackIncrement /= 1.2;
    }

    private void reset() {
        condenserAttackProgress = 0;
        turbineAttackProgress = 0;
        pump1AttackProgress = 0;
        pump2AttackProgress = 0;
    }
    
    private void decay(int steps) {        
        condenserAttackProgress -= steps * decayDecrement;
        turbineAttackProgress -= steps * decayDecrement;
        pump1AttackProgress -= steps * decayDecrement;
        pump2AttackProgress -= steps * decayDecrement;
        
        condenserAttackProgress = Math.max(0, condenserAttackProgress);
        turbineAttackProgress = Math.max(0, turbineAttackProgress);
        pump1AttackProgress = Math.max(0, pump1AttackProgress);
        pump2AttackProgress = Math.max(0, pump2AttackProgress);
    }
}
