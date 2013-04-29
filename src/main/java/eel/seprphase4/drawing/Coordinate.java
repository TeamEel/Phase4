package eel.seprphase4.drawing;

import java.awt.Point;

/**
 * Encapsulate an x,y coordinate pair
 * 
 * @author drm
 */
public class Coordinate {

    public int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Coordinate(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Coordinate plus(Coordinate other) {
        return new Coordinate(x + other.x, y + other.y);
    }
    
    public boolean northWestOf(Coordinate other) {
        return x <= other.x && y <= other.y;
    }
    
    public boolean southEastOf(Coordinate other) {
        return x >= other.x && y >= other.y;
    }
}
