/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.gui.controls;

import eel.seprphase4.gui.Control;
import eel.seprphase4.gui.drawable.DrawableText;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public class PageControl implements Control, ActionListener {

    private static final Font font = new Font("Courier New", Font.BOLD, 16);
    private static final Color color = new Color(255, 155, 0);
    private final ArrayList<Control> pages;
    private final ButtonControl nextControl;
    private final ButtonControl backControl;
    private final ButtonControl finishControl;
    private final ButtonControl exitControl;
    private final int x, y;
    private int currentPage;

    public PageControl(ButtonControl nextControl, ButtonControl backControl,
                       ButtonControl finishControl, ButtonControl exitControl,
                       int x, int y) {
        this.pages = new ArrayList<Control>();
        this.nextControl = nextControl;
        this.backControl = backControl;
        this.finishControl = finishControl;
        this.exitControl = exitControl;
        this.x = x;
        this.y = y;
        this.currentPage = 0;
        this.backControl.addActionListener(this);
        this.nextControl.addActionListener(this);
    }

    public void next() {
        if (!lastPage()) {
            currentPage++;
        }
    }

    public void back() {
        if (!firstPage()) {
            currentPage--;
        }
    }

    public void add(Control control) {
        pages.add(control);
    }

    public void add(String s) {
        pages.add(new ImageControl(new DrawableText(s, font, color), x, y));
    }

    @Override
    public void paint(Graphics g) {
        pages.get(currentPage).paint(g);
        if (firstPage()) {
            exitControl.paint(g);
        } else {
            backControl.paint(g);
        }
        if (lastPage()) {
            finishControl.paint(g);
        } else {
            nextControl.paint(g);
        }
    }

    @Override
    public void advance(int ms) {
        pages.get(currentPage).advance(ms);

        if (lastPage()) {
            nextControl.advance(ms);
        } else {
            finishControl.advance(ms);
        }
    }

    @Override
    public void onMouseExited() {
        nextControl.onMouseExited();
        backControl.onMouseExited();
        finishControl.onMouseExited();
        exitControl.onMouseExited();
        pages.get(currentPage).onMouseExited();
    }

    @Override
    public void onMouseMoved(Point point) {
        if (lastPage()) {
            finishControl.onMouseMoved(point);
        } else {
            nextControl.onMouseMoved(point);
        }
        if (firstPage()) {
            exitControl.onMouseMoved(point);
        } else {
            backControl.onMouseMoved(point);
        }
        pages.get(currentPage).onMouseMoved(point);
    }

    @Override
    public boolean onMousePressed(Point point) {
        if (firstPage()) {
            if (exitControl.onMousePressed(point)) {
                return true;
            }
        } else if (backControl.onMousePressed(point)) {
            return true;
        }
        if (lastPage()) {
            if (finishControl.onMousePressed(point)) {
                return true;
            }
        } else if (nextControl.onMousePressed(point)) {
            return true;
        }
        return pages.get(currentPage).onMousePressed(point);
    }

    @Override
    public boolean onMouseReleased(Point point) {
        if (firstPage()) {
            if (exitControl.onMouseReleased(point)) {
                return true;
            }
        } else if (backControl.onMouseReleased(point)) {
            return true;
        }
        if (lastPage()) {
            if (finishControl.onMouseReleased(point)) {
                return true;
            }
        } else if (nextControl.onMouseReleased(point)) {
            return true;
        }
        return pages.get(currentPage).onMouseReleased(point);
    }

    @Override
    public boolean onKeyTyped(KeyEvent e) {
        return false;
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        return false;
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == nextControl) {
            next();
        } else if (source == backControl) {
            back();
        }
    }

    private boolean firstPage() {
        return currentPage == 0;
    }

    private boolean lastPage() {
        return !(currentPage < pages.size() - 1);
    }
}
