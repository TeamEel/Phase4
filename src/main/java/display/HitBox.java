package display;

import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author David
 */
public class HitBox {

    private final Point topLeft;
    private final Point bottomRight;
    private static HitBox nullHitBox;

    public HitBox(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public boolean contains(Point p) {
        return p.x >= topLeft.x &&
               p.y >= topLeft.y &&
               p.x <= bottomRight.x &&
               p.y <= bottomRight.y;
    }

    public static HitBox fromImage(Image image, int x, int y) {
        return new HitBox(new Point(x, y),
                          new Point(x + image.getWidth(null),
                                    y + image.getHeight(null)));
    }

    public static HitBox nullHitBox() {
        if (nullHitBox == null) {
            nullHitBox = new HitBox(null, null) {
                @Override
                public boolean contains(Point p) {
                    return false;
                }
            };
        }
        return nullHitBox;
    }
}
