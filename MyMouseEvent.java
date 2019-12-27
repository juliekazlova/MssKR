import java.awt.*;
import java.util.EventObject;

public class MyMouseEvent extends EventObject {
   private int xCoordinate;
    private int yCoordinate;
    private Color color;

    public MyMouseEvent(Object source, int xCoordinate, int yCoordinate, Color color) {
        super(source);
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.color = color;
    }


    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public Color getColor() {
        return color;
    }
}
