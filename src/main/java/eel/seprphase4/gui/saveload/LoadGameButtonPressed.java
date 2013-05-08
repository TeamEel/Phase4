package eel.seprphase4.gui.saveload;


public class LoadGameButtonPressed extends LoadGameButtonOver {

    public LoadGameButtonPressed(String username, String timestamp) {
        super(username, timestamp);
    }
    
    @Override
    protected int xOffset() {
        return 3;
    }
    
    @Override
    protected int yOffset() {
        return 3;
    }
}
