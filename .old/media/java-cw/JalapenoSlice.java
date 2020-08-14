import java.awt.*;
/**
 * Jalapeno Slice Topping subclass
 * 
 * @author up850844
 * 
 */
public class JalapenoSlice extends Topping
{
    /**
     * Constructor for objects of class JalapenoSlice
     */
    public JalapenoSlice(Canvas jalapenoCanvas)
    {
        super(jalapenoCanvas);
    }
    
    /**
     * The method to draw the jalapeno slice topping
     * @param xCoord The x position of the topping
     * @param yCoord The y position of the topping
     */
    public void drawJalapenoSlice(double xCoord, double yCoord)
    {
        canvas.setForegroundColor(Color.GREEN);
        canvas.fillCircle(xCoord, yCoord, 30);
        canvas.setForegroundColor(Color.WHITE);
        canvas.fillCircle(xCoord, yCoord, 20);
        canvas.setForegroundColor(Color.BLACK);
        for(int i=0; i < 3; i++)
        {
            canvas.fillCircle(xCoord, yCoord + i*6.67 - 6.67, 6.67);
        }
        for(int i=0; i < 3; i++)
        {
            canvas.fillCircle(xCoord + i*6.67 - 6.67, yCoord, 6.67);
        }
    }
}
