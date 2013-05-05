/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import eel.seprphase4.Simulator.PlantController;
import eel.seprphase4.Simulator.PlantStatus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import eel.seprphase4.Utilities.Percentage;
/**
 *
 * @author James
 */
public class ControlRodPositionControl extends CompositeControl implements ActionListener {
   
    private PlantController controller;
    private PlantStatus status;
    private int x,y;
    
    public ControlRodPositionControl(PlantController controller,PlantStatus status, int x, int y) {
        this.controller = controller;
        this.status = status;
        this.x=x;
        this.y=y;
        addWithActionListner(new NamedButtonControl("up",Asset.PlantUpArrow,Asset.PlantUpArrowOver, Asset.PlantUpArrow,x,y),5);
        addWithActionListner(new NamedButtonControl("down",Asset.PlantDownArrow,Asset.PlantDownArrowOver, Asset.PlantDownArrow,x,y+100),5);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(((NamedButtonControl)ae.getSource()).getName().equals("up")) {
            if(status.controlRodPosition().points()<100)
            {
                controller.moveControlRods(
                    new Percentage(
                        (status.controlRodPosition().points()+10)-((status.controlRodPosition().points())%10)
                    )
                    );
            }
        }
        else if(((NamedButtonControl)ae.getSource()).getName().equals("down"))
        {
            if(status.controlRodPosition().points()>=10)
            {
                controller.moveControlRods(
                    new Percentage(
                        (status.controlRodPosition().points()-10)-((status.controlRodPosition().points())%10)
                    )
                    );
            }
        }
    }
    
    private void addWithActionListner(ButtonControl c,int zindex) {
        c.addActionListener(this);
        this.add(c,zindex);
    }
}
