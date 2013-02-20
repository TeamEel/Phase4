package cichlid.seprphase3.GUIInterface;

import java.awt.event.MouseEvent;
import javax.swing.JPanel;


public class BaseInterface extends JPanel {
    
    public boolean clicked(PlantGUIElement element, MouseEvent click) {
        return element.location.contains(click.getPoint());
    }
    
}
