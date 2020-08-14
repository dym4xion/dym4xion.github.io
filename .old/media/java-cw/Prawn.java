import java.awt.*;
/**
 * Prawn Topping subclass
 * 
 * @author up850844
 * 
 */
public class Prawn extends Topping
{
    /**
     * Constructor for objects of class Prawn
     */
    public Prawn(Canvas prawnCanvas)
    {
        super(prawnCanvas);
    }
    
    /**
     * The method to draw the prawn topping
     * @param xCoord The x position of the topping
     * @param yCoord The y position of the topping
     */
    public void drawPrawn(double xCoord, double yCoord)
    {
        canvas.setForegroundColor(Color.PINK);
        canvas.fillCircle(xCoord, yCoord, 20);
        double[] tailXPoints = {xCoord+7, xCoord+17, xCoord+13, xCoord-6, xCoord+10, xCoord+12, xCoord};
        double[] tailYPoints = {yCoord-7, yCoord+3, yCoord+16, yCoord+19, yCoord+14, yCoord+6, yCoord};
        canvas.fillPolygon(tailXPoints, tailYPoints);
    }
}
