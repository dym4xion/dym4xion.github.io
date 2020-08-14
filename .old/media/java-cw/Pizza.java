import java.awt.*;

/**
 * Class to represent a single pizza.
 * @author UP850844
 */
public class Pizza
{
    private Canvas canvas;
    private double topLeftX;
    private double topLeftY;
    private int size;
    private String crust;
    private String sauce;
    private int numTop;
    private String top1;
    private String top2;
    private int zaNum;
    private double price; // stays 0 until set otherwise

    /**
     * Constructor for pizza.
     * @param win The window to draw the pizza on.
     * @param startX The top-left x coordinate for the section of screen to draw pizza on.
     * @param startY The top-left y coordinate for the section of screen to draw pizza on.
     * @param pizzaSize The size of the pizza. Either 10, 12 or 14 inches.
     * @param crustType The type of crust the pizza can have. Either deep pan, thin crust or stuffed crust.
     * @param sauceType The type of sauce on pizza. Either BBQ or Tomato.
     * @param numberOfToppings Number of unique toppings on the pizza. Either 1 or 2.
     * @param firstTopping First Topping on the Pizza. Will have 5 drawings.
     * @param secondTopping Second Topping on the Pizza. Will have 4 drawings. If the same topping is entered twice, toppings will be layed out as if one topping is chosen.
     */
    public Pizza(Canvas pizzaCanvas, double pizzaTopLeftX, double pizzaTopLeftY, int pizzaSize, String crustType, String sauceType, int numberOfToppings, String firstTopping, String secondTopping)
    {
        canvas = pizzaCanvas;
        topLeftX = pizzaTopLeftX;
        topLeftY = pizzaTopLeftY;
        size = pizzaSize;
        crust = crustType;
        sauce = sauceType;
        numTop = numberOfToppings;
        top1 = firstTopping;
        top2 = secondTopping;
        zaNum = 0;
        price = 0;
    }

    //START OF MUTATOR METHODS

    /**
     * changes the canvas value of the pizza
     * @param newCanvas the new canvas value for the pizza
     */
    public void setCanvas(Canvas newCanvas)
    {
        canvas = newCanvas;
    }

    /**
     * changes the top left x position of the pizza
     * @param newTopLeftX new value for the top left x position of the pizza
     */
    public void setTopLeftX(double newTopLeftX)
    {
        topLeftX = newTopLeftX;
    }

    /**
     * changes the top left y position of the pizza
     * @param newTopLeftY new value for the top left y position of the pizza
     */
    public void setTopLeftY(double newTopLeftY)
    {
        topLeftY = newTopLeftY;
    }

    /**
     * changes the diameter size of the pizza
     * @param newSize the new diameter size of the pizza
     */
    public void setSize(int newSize)
    {
        size = newSize;
    }

    /**
     * changes the pizza crust type
     * @param newCrust the new pizza crust type
     */
    public void setCrust(String newCrust)
    {
        crust = newCrust;
    }

    /**
     * changes the pizza sauce type
     * @param newSauce the new sauce type
     */
    public void setSauce(String newSauce)
    {
        sauce = newSauce;
    }

    /**
     * changes the number pizza toppings
     * @param newNumTop the new value for the number of toppings
     */
    public void setNumTop(int newNumTop)
    {
        numTop = newNumTop;
    }

    /**
     * changes the value for the first topping
     * @param newTop1 the new value for the first topping
     */
    public void setTop1(String newTop1)
    {
        top1 = newTop1;
    }

    /**
     * changes the value for the second topping
     * @param newTop2 the new value for the second topping
     */
    public void setTop2(String newTop2)
    {
        top2 = newTop2;
    }
    
    /**
     * changes the pizza number
     * @param new pizza number
     */
    public void setZaNum(int newZaNum)
    {
        zaNum = newZaNum;
    }
    
    /**
     * sets the price of the pizza
     */
    public void setPrice(double newPrice)
    {
        price = newPrice;
    }
    
    //END OF MUTATOR METHODS
    //START OF ACCESSOR METHODS

    /**
     * returns the pizza's canvas
     * @return pizza canvas
     */
    public Canvas getCanvas()
    {
        return canvas;
    }

    /**
     * returns the pizza's top left x value
     * @return pizza's top left x value
     */
    public double getTopLeftX()
    {
        return topLeftX;
    }

    /**
     * returns the pizza's top left y value
     * @return pizza's top left y value
     */
    public double getTopLeftY()
    {
        return topLeftY;
    }

    /**
     * returns the pizza's size value
     * @return pizza's size
     */
    public int getSize()
    {
        return size;
    }

    /**
     * returns the pizza's crust value
     * @return pizza'z crust type
     */
    public String getCrust()
    {
        return crust;
    }

    /**
     * returns the pizza's sauce value
     * @return pizza sauce type
     */
    public String getSauce()
    {
        return sauce;
    }

    /**
     * returns the pizza's number of topping types
     * @return number of pizza topping types used
     */
    public int getNumTop()
    {
        return numTop;
    }

    /**
     * returns the pizza's first topping
     * @return first topping type
     */
    public String getTop1()
    {
        return top1;
    }

    /**
     * returns the pizza's second topping
     * @return second topping type
     */
    public String getTop2()
    {
        return top2;
    }
    
    /**
     * @return the price of a the pizza
     */
    public double getPrice()
    {
        return price;
    }
    
    /**
     * @return the pizza number
     */
    public int getZaNum()
    {
        return zaNum;
    }
    //END OF ACCESSOR METHODS

    /**
     * Method to display the pizza and pizza information on the canvas.
     */
    public void displayPizza()
    {
        drawPizza();
        drawTopLine();
        drawBottomLine();
    }

    /**
     * Method to display the pizza on the screen.
     * 
     */
    public void drawPizza()
    {
        canvas.setForegroundColor(Color.YELLOW);
        canvas.fillCircle(topLeftX + 150, topLeftY + 150, 200);
        drawSauce();
        drawMozz();
        drawTop1();
        drawTop2();        
    }
    
    /**
     * Method to draw the sauce layer of the pizza
     */
    public void drawSauce()
    {
        if(sauce.equalsIgnoreCase("tomato"))
        {
            canvas.setForegroundColor(Color.RED);            
        }
        else
        {
            canvas.setForegroundColor(Color.ORANGE);
        }
        canvas.fillCircle(topLeftX + 150, topLeftY + 150, 180);
    }
    
    /**
     * Method to draw the mozzarella layer of the pizza
     */
    public void drawMozz()
    {
        canvas.setForegroundColor(Color.WHITE);
        canvas.fillCircle(topLeftX + 150, topLeftY + 150, 160);
    }
    
    /**
     * Draws five implementations of the first topping chosen by the user
     */
    public void drawTop1()
    {
        if(top1.equalsIgnoreCase("prawn"))
        {
            Prawn p = new Prawn(canvas);
            p.drawPrawn(topLeftX + 110, topLeftY + 110);
            p.drawPrawn(topLeftX + 190, topLeftY + 110);
            p.drawPrawn(topLeftX + 150, topLeftY + 150);
            p.drawPrawn(topLeftX + 110, topLeftY + 190);
            p.drawPrawn(topLeftX + 190, topLeftY + 190);
        }
        else
        {
            JalapenoSlice j = new JalapenoSlice(canvas);
            j.drawJalapenoSlice(topLeftX + 105, topLeftY + 105);
            j.drawJalapenoSlice(topLeftX + 195, topLeftY + 105);
            j.drawJalapenoSlice(topLeftX + 150, topLeftY + 150);
            j.drawJalapenoSlice(topLeftX + 105, topLeftY + 195);
            j.drawJalapenoSlice(topLeftX + 195, topLeftY + 195);
        }
    }
    
    /**
     * Draws four implementations of the second topping chosen by the user
     */
    public void drawTop2()
    {
        if(top2.equalsIgnoreCase("prawn"))
        {
            Prawn p = new Prawn(canvas);
            p.drawPrawn(topLeftX + 150, topLeftY + 100);
            p.drawPrawn(topLeftX + 100, topLeftY + 150);
            p.drawPrawn(topLeftX + 200, topLeftY + 150);
            p.drawPrawn(topLeftX + 150, topLeftY + 200);
        }
        else
        {
            JalapenoSlice j = new JalapenoSlice(canvas);
            j.drawJalapenoSlice(topLeftX + 150, topLeftY + 100);
            j.drawJalapenoSlice(topLeftX + 100, topLeftY + 150);
            j.drawJalapenoSlice(topLeftX + 200, topLeftY + 150);
            j.drawJalapenoSlice(topLeftX + 150, topLeftY + 200);
        }
    }

    /**
     * Method to write the information shown in the top line of the
     * individual pizza on the screen.
     * This method will display the pizza number and size at the top of the
     * screen
     */
    private void drawTopLine()
    {
        String sizeString;
        if(size == 10)
        {
            sizeString = "Small";
        }
        else if(size == 12)
        {
            sizeString = "Medium";
        }
        else
        {
            sizeString = "Large";
        }
        
        String topLine = "Pizza " + zaNum + " {" + sizeString + "}";

        double stringX = topLeftX+10;
        double stringY = topLeftY + 25;

        canvas.setForegroundColor(Color.BLACK);
        canvas.setFontSize(15);
        canvas.drawString(topLine, stringX, stringY);
    }

    /**
     * Method to write the information shown in the bottom line of the
     * individual pizza on the screen.
     * This method will display the type of crust and sauce ordered (once
     * completed)
     */
    private void drawBottomLine()
    {
        String bottomLine = "Crust: " + crust + "; Sauce: " + sauce;

        double stringX = topLeftX+10;
        double stringY = topLeftY + 290;

        canvas.setForegroundColor(Color.BLACK);
        canvas.setFontSize(15);
        canvas.drawString(bottomLine, stringX, stringY);
    }
    
    /**
     * Erases a drawn pizza
     */
    public void erasePizza()
    {
        canvas.setForegroundColor(Color.WHITE);
        canvas.fillRectangle(topLeftX+1, topLeftY+1, 298, 298); 
    }
}
