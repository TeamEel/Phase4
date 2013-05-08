/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display.screens;

import display.Asset;
import display.controls.ButtonControl;
import display.controls.ImageControl;
import display.controls.PageControl;
import java.awt.event.ActionListener;

/**
 *
 * @author drm511
 */
public abstract class CutScreen extends MenuScreen implements ActionListener {

    protected final ButtonControl exitButton;
    protected final ButtonControl startButton;
    protected final PageControl pages;

    public CutScreen() {
        exitButton = new ButtonControl(Asset.BackDefault,
                                       Asset.BackOver,
                                       Asset.BackPressed,
                                       LEFT_MARGIN, TOP_MARGIN + 350);
        startButton = new ButtonControl(Asset.StartDefault,
                                        Asset.StartOver,
                                        Asset.StartPressed,
                                        LEFT_MARGIN + 400, TOP_MARGIN + 350);
        pages = new PageControl(new ButtonControl(Asset.NextDefault,
                                                  Asset.NextOver,
                                                  Asset.NextPressed,
                                                  LEFT_MARGIN + 400, TOP_MARGIN + 350),
                                new ButtonControl(Asset.BackDefault,
                                                  Asset.BackOver,
                                                  Asset.BackPressed,
                                                  LEFT_MARGIN, TOP_MARGIN + 350),
                                startButton, exitButton, 600, 300);

        add(pages, 5);

        add(new ImageControl(Asset.MenuScientists, -100, 200), 3);

        exitButton.addActionListener(this);
        startButton.addActionListener(this);
    }
}
