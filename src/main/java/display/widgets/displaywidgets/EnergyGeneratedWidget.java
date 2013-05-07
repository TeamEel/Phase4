/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.widgets.displaywidgets;

import display.drawable.DrawableText;
import eel.seprphase4.Simulator.Simulator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class EnergyGeneratedWidget extends DisplayWidget {

    private final Font font;
    private final DrawableText text;

    public EnergyGeneratedWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y);
        this.font = new Font("Courier New", Font.BOLD, 30);
        this.text = new DrawableText("Energy Generated: ", font, Color.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        text.setText("Energy Generated: " + simulator.energyGenerated());
        text.draw(g, x, y);
    }

    @Override
    public void advance(int ms) {
        // ignore
    }
}
