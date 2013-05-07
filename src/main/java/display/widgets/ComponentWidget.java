//package display.widgets;
//
//import display.Asset;
//import display.DrawableFactory;
//import display.HitBox;
//import display.drawable.Drawable;
//import eel.seprphase4.Simulator.Simulator;
//import java.awt.Graphics;
//import java.awt.Point;
//
///**
// *
// * @author David
// */
//public abstract class ComponentWidget extends ClickableWidget {
//
//    private enum State {
//
//        On,
//        Off,
//        Pressed
//    }
//    private final Drawable onDraw;
//    private final Drawable onOverDraw;
//    private final Drawable offDraw;
//    private final Drawable offOverDraw;
//    private final Drawable failDraw;
//    private final Drawable failOverDraw;
//    private final HitBox hitBox;
//    private boolean mouseIsOver;
//
//    public ComponentWidget(Simulator simulator, int x, int y,
//                           Asset onDraw, Asset onOverDraw,
//                           Asset offDraw, Asset offOverDraw,
//                           Asset failDraw, Asset failOverDraw) {
//        super(simulator, x, y);
//        this.onDraw = DrawableFactory.create(onDraw);
//        this.onOverDraw = DrawableFactory.create(onOverDraw);
//        this.offDraw = DrawableFactory.create(offDraw);
//        this.offOverDraw = DrawableFactory.create(offOverDraw);
//        this.failDraw = DrawableFactory.create(failDraw);
//        this.failOverDraw = DrawableFactory.create(failOverDraw);
//        this.hitBox = this.onDraw.hitBox(x, y);
//    }
//
//    @Override
//    public void paint(Graphics g) {
//        currentDraw(mouseIsOver).draw(g, x, y);
//    }
//
//    @Override
//    public void advance(int ms) {
//        currentDraw(true).advance(ms);
//        currentDraw(false).advance(ms);
//    }
//
//    @Override
//    public void onMouseExited() {
//        mouseIsOver = false;
//    }
//
//    @Override
//    public void onMouseMoved(Point point) {
//        mouseIsOver = hitBox.contains(point);
//    }
//
//    @Override
//    public boolean onMousePressed(Point point) {
//        if (hitBox.contains(point)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public boolean onMouseReleased(Point point) {
//        if (hitBox.contains(point)) {
//            if (hasFailed()) {
//                repair();
//            } else if (isOn()) {
//                turnOff();
//            } else {
//                turnOn();
//            }
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private Drawable currentDraw(boolean over) {
//        if (over) {
//            if (isOn()) {
//                return onDraw;
//            } else if (hasFailed()) {
//                return failDraw;
//            } else {
//                return offDraw;
//            }
//        } else {
//            if (isOn()) {
//                return onOverDraw;
//            } else if (hasFailed()) {
//                return failOverDraw;
//            } else {
//                return offOverDraw;
//            }
//        }
//    }
//
//    protected abstract void repair();
//
//    protected abstract void turnOn();
//
//    protected abstract void turnOff();
//
//    protected abstract boolean isOn();
//
//    protected abstract boolean hasFailed();
//}
