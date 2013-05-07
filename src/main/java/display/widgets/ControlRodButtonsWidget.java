package display.widgets;

import display.Asset;
import display.controls.ButtonControl;
import eel.seprphase4.Simulator.Simulator;
import eel.seprphase4.Utilities.Percentage;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author David
 */
public class ControlRodButtonsWidget extends Widget implements ActionListener {

    private final ButtonControl upButton;
    private final ButtonControl downButton;

    public ControlRodButtonsWidget(Simulator simulator, int x, int y) {
        super(simulator, x, y);
        this.upButton = new ButtonControl(Asset.PlantUpArrow,
                                          Asset.PlantUpArrowOver,
                                          Asset.PlantUpArrow,
                                          x, y);
        this.downButton = new ButtonControl(Asset.PlantDownArrow,
                                            Asset.PlantDownArrowOver,
                                            Asset.PlantDownArrow,
                                            x, y + 100);
        upButton.addActionListener(this);
        downButton.addActionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        upButton.paint(g);
        downButton.paint(g);
    }

    @Override
    public void advance(int ms) {
        upButton.advance(ms);
        downButton.advance(ms);
    }

    @Override
    public void onMouseExited() {
        upButton.onMouseExited();
        downButton.onMouseExited();
    }

    @Override
    public void onMouseMoved(Point point) {
        upButton.onMouseMoved(point);
        downButton.onMouseMoved(point);
    }

    @Override
    public boolean onMousePressed(Point point) {
        return upButton.onMousePressed(point) ||
               downButton.onMousePressed(point);
    }

    @Override
    public boolean onMouseReleased(Point point) {
        return upButton.onMouseReleased(point) ||
               downButton.onMouseReleased(point);
    }

    @Override
    public boolean onKeyTyped(KeyEvent e) {
        // ignore
        return false;
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        // ignore
        return false;
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        // ignore
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        double position = simulator.controlRodPosition().points();
        if (source == upButton) {
            position += 10;
        } else if (source == downButton) {
            position -= 10;
        }
        if (position > 100) {
            position = 100;
        }
        if (position < 0) {
            position = 0;
        }
        simulator.moveControlRods(new Percentage(position));
    }
}
