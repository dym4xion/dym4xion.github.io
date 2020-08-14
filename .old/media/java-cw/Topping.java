
/**
 * Superclass for the two implemented toppings
 * @author up850844
 */
public class Topping
{
    protected Canvas canvas;
    /**
     * Constructor for objects of class Topping
     */
    public Topping(Canvas toppingCanvas)
    {
        canvas = toppingCanvas;
        
    }
    
    /**
     * @return The canvas of the topping
     */
    public Canvas getCanvas()
    {
        return canvas;
    }
}
