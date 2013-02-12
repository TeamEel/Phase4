/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Game;
import cichlid.seprphase3.Simulator.GameManager;
import cichlid.seprphase3.Simulator.PlantController;
import cichlid.seprphase3.Simulator.PlantStatus;
import cichlid.seprphase3.Simulator.Simulator;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.Label;
import java.awt.TextField;

public class NameInterface extends JPanel implements ActionListener
{
    private GUIWindow parent;
    private Simulator simulator;
    
    private Label namelbl;
    private TextField nameField;
    public String name;
   
    
    public NameInterface(GUIWindow _parent, Simulator _simulator)
    {
        parent = _parent;
        simulator = _simulator;
        setUpComponents();
    }
    
    private void setUpComponents()
    {
        setLayout(null);
        
        namelbl = new Label("Please enter you name:");
        add(namelbl);
        namelbl.setBounds(100, 100, 140, 30);
        
        nameField = new TextField();
        add(nameField);
        nameField.setBounds(240, 100, 300, 30);
        nameField.addActionListener(this);
        
        name = nameField.getText();
        simulator.setUsername(name);
        parent.setWindow(new PlantInterface(simulator, simulator, simulator));
        
    }
    
    @Override
    public void actionPerformed(ActionEvent evt)
    {
       parent.setWindow(new PlantInterface(simulator, simulator, simulator)); 
    }
    
    
}
