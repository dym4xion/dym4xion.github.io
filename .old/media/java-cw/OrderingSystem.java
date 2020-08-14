import java.awt.*;
import java.util.*;

/**
 * Class to manage ordering pizzas.
 * @author UP850844
 */
public class OrderingSystem
{
    private Canvas canvas;
    private KeyboardInput pizzaInputs;
    private ArrayList<Pizza> PizzaList;
    private double[] xPositions;
    private double[] yPositions;
    private double orderTotal; // in pounds
    private ArrayList<Canvas> CanvasArray;
    private int posTracker;
    private String stringOut;
    private int enteredPizzas;

    /**
     * Constructor for the ordering system.
     */
    public OrderingSystem()
    {
        canvas = new Canvas("Pizza Ordering", 900, 650);
        pizzaInputs = new KeyboardInput();
        PizzaList = new ArrayList<Pizza>();
        xPositions = new double[]{0, 300, 600, 0, 300, 600};
        yPositions = new double[]{0, 0, 0, 300, 300, 300};
        orderTotal = 0.00;
        CanvasArray = new ArrayList<Canvas>();
        posTracker = 0;
        stringOut = "";
        enteredPizzas = 0;
    }

    /**
     * Getter method for the ordering system canvas
     * @return the ordering system canvas
     */
    public Canvas getCanvas()
    {
        return canvas;
    }

    /**
     * Method to manage the ordering of the pizzas (once completed).
     */
    public void startOrdering()
    {
        boolean running = true;

        while(running)
        {
            System.out.println("\nEnter action:\nNew Pizza: Type 'N'.\nRemove Pizza: Type 'R'.\nModify Pizza: 'M'.\nQuit: 'Q'.");
            String action = pizzaInputs.getInputString();
            if(action.equalsIgnoreCase("N"))
            {
                addPizza();
            }
            else if(action.equalsIgnoreCase("Q"))
            {
                running = false;
            }
            else if(action.equalsIgnoreCase("R")) //WHAT IF THE PIZZA THEY ENTER DOESN'T EXIST
            {
                removePizza();
            }
            else if(action.equalsIgnoreCase("M"))//WHAT IF THE PIZZA THEY ENTER DOESN'T EXIST
            {
                modifyPizza();
            }
            else
            {
                System.out.println("Incorrect action value input!");
            }
            System.out.println("\nTotal Order Price: £" + String.format("%.2f", orderTotal));
        }
    }

    // USER METHODS
    /**
     * Gets input from the user to create and then draw a pizza
     */
    public void addPizza()
    {
        enteredPizzas++;
        Pizza p = getPizzaData();
        p.setZaNum(enteredPizzas);

        eraseOrderTotal();
        CalculatePrice calc = new CalculatePrice(p);
        //pizza total calculation od divided by 100 as the price is calculated in pence
        double pizzaTotal = calc.returnPizzaTotal() / 100;
        p.setPrice(pizzaTotal);
        orderTotal += pizzaTotal;
        drawOrderScreen();
        if((enteredPizzas%7) == 0)
        {
            eraseOrderTotal();
            canvas = new Canvas("Another Ordering Window", 900, 650);
            drawOrderScreen();
        }
        p.setCanvas(canvas);
        p.displayPizza();
    }

    /**
     * Removes a pizza and refeshes the order price
     */
    public void removePizza()
    {
        System.out.println("\nEnter the Pizza number you would like to remove:");
        int userPizzaNumber = pizzaInputs.getInputInteger();

        boolean removed = false;
        for(Pizza p : PizzaList)
        {

            if(p.getZaNum() == userPizzaNumber)
            {
                Pizza sp;
                sp = p;
                sp.erasePizza();
                double priceToSubtract = sp.getPrice();
                orderTotal -= priceToSubtract;
                eraseOrderTotal();
                drawOrderScreen();
                removed = true;
                PizzaList.remove(sp);
                break;
            }

        }

        if(!removed)
        {
            System.out.println("Pizza does not exist.");
        }

    }

    /**
     * Method to instigate pizza modification
     */
    public void modifyPizza()
    {
        System.out.println("\nEnter the Pizza number you would like to modify:");
        int userPizzaNumber = pizzaInputs.getInputInteger();
        Pizza sp;
        boolean modified = false;

        for(Pizza p : PizzaList)
        {
            if(p.getZaNum() == userPizzaNumber)
            {
                sp = p;
                System.out.println("Which feature would you like to modify? ('size', 'crust', 'sauce', 'topping 1', 'topping 2')");
                String userModify = pizzaInputs.getInputString();
                if(userModify.equalsIgnoreCase("size"))
                {
                    modifySize(p);
                    modified = true;
                }
                else if(userModify.equalsIgnoreCase("crust"))
                {
                    modifyCrust(p);
                    modified = true;
                }
                else if(userModify.equalsIgnoreCase("sauce"))
                {
                    modifySauce(p);
                    modified = true;
                }
                else if(userModify.equalsIgnoreCase("topping 1"))
                {
                    modifyTop1(p);
                    modified = true;
                }
                else if(userModify.equalsIgnoreCase("topping 2"))
                {
                    modifyTop2(p);
                    modified = true;
                }
                else
                {
                    System.out.println("Incorrect input. Try Again!");
                }
            }
        }
        if(!modified)
        {
            System.out.println("Pizza does not exist.");
        }
    }

    /**
     * Modifys a pizza size
     * @param the pizza number output to the screen
     */
    public void modifySize(Pizza p)
    {
        //take away from total price here
        double oldPrice = p.getPrice();
        orderTotal -= oldPrice;

        int curSize = p.getSize();
        System.out.println("Current size is " + curSize + ". What would you like to change it to?");
        int newSize = inputSize();
        p.setSize(newSize);
        System.out.println("Pizza " + p.getZaNum()+ "'s size has changed to " + p.getSize());

        refreshPrice(p); 
    }

    /**
     * Modifys a pizza crust
     * @param the pizza number output to the screen
     */
    public void modifyCrust(Pizza p)
    {
        //take away from total price here
        double oldPrice = p.getPrice();
        orderTotal -= oldPrice;

        String curCrust = p.getCrust();
        System.out.println("Current crust is " + curCrust + ". What would you like to change it to?");
        String newCrust = inputCrust();
        p.setCrust(newCrust);
        System.out.println("Pizza " + p.getZaNum() + "'s crust has changed to " + p.getCrust());

        refreshPrice(p);
    }

    /**
     * Modifys a pizza sauce
     * @param the pizza number output to the screen
     */
    public void modifySauce(Pizza p)
    {
        //take away from total price here
        double oldPrice = p.getPrice();
        orderTotal -= oldPrice;

        String curSauce = p.getSauce();
        System.out.println("Pizza " + p.getZaNum() + "'s sauce was " + curSauce + ". Would you like to change it?");
        String newSauce = inputSauce();
        p.setSauce(newSauce);
        System.out.println("Pizza " + p.getZaNum() + "'s sauce has changed to " + p.getSauce());

        refreshPrice(p);
    }

    /**
     * Modifys a pizza's the first topping
     * @param the pizza number output to the screen
     */
    public void modifyTop1(Pizza p)
    {
        //take away from total price here
        double oldPrice = p.getPrice();
        orderTotal -= oldPrice;

        String curTop1 = p.getTop1();
        System.out.println("Current Topping 1 is " + curTop1 + ". What would you like to change it to?");
        String newTop1 = inputTop1();
        p.setTop1(newTop1);
        System.out.println("Pizza " + p.getZaNum() + "'s Topping 1 has changed to " + p.getTop1());

        refreshPrice(p);
    }

    /**
     * Modifys a pizza's second topping
     * @param the pizza number output to the screen
     */
    public void modifyTop2(Pizza p)
    {
        //take away from total price here
        double oldPrice = p.getPrice();
        orderTotal -= oldPrice;

        String curTop2 = p.getTop2();
        System.out.println("Current Topping 2 is " + curTop2 + ". What would you like to change it to?");
        String newTop2 = inputTop2();
        p.setTop2(newTop2);
        System.out.println("Pizza " + p.getZaNum() + "'s Topping 2 has changed to " + p.getTop2());

        refreshPrice(p);
    }
    //END OF USER METHODS

    /**
     * Refreshes a pizza's price. Used after a pizza is modified
     * @param the pizza being modified
     */
    public void refreshPrice(Pizza p)
    {
        CalculatePrice newPriceCalc = new CalculatePrice(p);
        double newPrice = newPriceCalc.returnPizzaTotal() / 100;
        p.setPrice(newPrice);
        orderTotal += newPrice;
        refreshPizza(p);
    }

    /**
     * Refreshed the visual output of a pizza after modification
     * @param the pizza being modified
     */
    public void refreshPizza(Pizza p)
    {
        p.erasePizza();
        eraseOrderTotal();
        p.displayPizza();
        drawOrderScreen();
    }

    /**
     * Method to draw the outline of the order screen.
     * Redrawn every time a pizza is added.
     */
    public void drawOrderScreen()
    {
        canvas.setForegroundColor(Color.BLACK);
        // vertical dividers
        canvas.drawLine(300, 0, 300, 600);
        canvas.drawLine(600, 0, 600, 600);

        // halfway divider
        canvas.drawLine(0, 300, 900, 300);

        // total price line
        canvas.drawLine(0, 600, 900, 600);
        canvas.setFontSize(25);

        String priceString = "Total Price of the Order: £";
        stringOut = "";
        stringOut = priceString + String.format("%.2f", orderTotal);
        canvas.drawString(stringOut, 10, 640);
    }

    /**
     * Method to erase the string which displays the total order price
     */
    public void eraseOrderTotal()
    {
        canvas.setForegroundColor(Color.WHITE);
        canvas.fillRectangle(0, 602, 900, 900);
    }

    //METHODS WHICH DESCRIBE USER INPUT 

    /**
     * Method to interface with the user to gain pizza data
     * @return A Pizza instance with the features as described by the user
     */
    public Pizza getPizzaData()
    {
        int size = inputSize();
        String crust = inputCrust();
        String sauce = inputSauce();
        int numTop = inputNumTop();
        String top1;
        String top2;
        if(numTop == 1)
        {
            top1 = inputTop1();
            top2 = top1;
        }
        else
        {
            top1 = inputTop1();
            top2 = inputTop2();
        }
        System.out.println("You have chosen " + top1 + " as your first topping, and " + top2 + " as your second topping.");

        Pizza p = new Pizza(canvas, xPositions[posTracker], yPositions[posTracker], size, crust, sauce, numTop, top1, top2);
        if(posTracker == 5)
        {
            posTracker = 0;
        }
        else
        {
            posTracker++;
        }

        PizzaList.add(p);
        return p;
    }

    /**
     * method to input pizza size
     * @return pizza size
     */
    public int inputSize()
    {
        //get valid pizza size
        int size;
        boolean validSize = false;
        do
        {
            System.out.println("\nEnter Pizza Size.\nEnter: '10' for Small,\nEnter: '12' for Medium and\nEnter: '14' for Large. ");
            size = pizzaInputs.getInputInteger();
            if(size != 10 && size != 12 && size != 14)
            {
                System.out.println("Please enter either: '10', '12'or '14'");
            }
            else
            {
                validSize = true;
            }
        }while(!validSize);
        System.out.println("You have chosen a " + size + " inch pizza.");
        return size;
    }

    /**
     * method to input pizza crust type
     * @return pizza crust type
     */
    public String inputCrust()
    {
        //get valid crust type
        String crust;
        boolean validCrust = false;
        do
        {
            System.out.println("\nEnter crust type (Deep Pan, Thin Crust, Stuffed Crust): ");
            crust = pizzaInputs.getInputString();
            if((crust.equalsIgnoreCase("Deep Pan") == false) && (crust.equalsIgnoreCase("Thin Crust") == false) && (crust.equalsIgnoreCase("Stuffed Crust") == false))
            {
                System.out.println("Please enter either: 'Deep Pan', 'Thin Crust' or 'Stuffed Crust' (case insensitive).");                
            }
            else
            {
                validCrust = true;
            }
        }while(!validCrust);
        System.out.println("You have chosen a pizza that has a " + crust + ".");
        return crust;
    }

    /**
     * method to input pizza sauce type
     * @return pizza sauce type
     */
    public String inputSauce()
    {
        //get valid change sauce response
        String sauce = "Tomato";
        boolean validSauce = false;
        do
        {
            System.out.println("\nChange sauce from Tomato to BBQ? (Y/N): ");
            String yorn = pizzaInputs.getInputString();
            if((yorn.equalsIgnoreCase("Y") == false) && (yorn.equalsIgnoreCase("N") == false))
            {
                System.out.println("Please enter either: 'Y' or 'N' (case insensitive)");
            }
            else
            {
                if(yorn.toUpperCase().equals("Y"))
                {
                    sauce = "BBQ";
                }
                validSauce = true;
            }
        }while(!validSauce);
        System.out.println("You have chosen a pizza that has " + sauce + " sauce.");
        return sauce;
    }

    /**
     * method to input the number of toppings
     * @return number of pizza topping types
     */
    public int inputNumTop()
    {
        //get valid number of topping types
        int numTop;
        boolean validTopNum = false;
        do
        {
            System.out.println("\nNumber of topping types (1/2): ");
            numTop = pizzaInputs.getInputInteger();
            if(numTop != 1 && numTop != 2)
            {
                System.out.println("Please enter either: '1' or '2'");
            }
            else
            {
                validTopNum = true;
            }
        }while(!validTopNum);
        System.out.println("You have chosen to have " + numTop + " topping type(s).");
        return numTop;
    }

    /**
     * method to input first topping
     * @return first pizza topping type
     */
    public String inputTop1()
    {
        String top1;
        boolean validTop = false;
        do
        {
            System.out.println("\nEnter Topping 1 (Prawn / Jalapeno Slice) [5 pieces]: ");
            top1 = pizzaInputs.getInputString();
            if((top1.equalsIgnoreCase("prawn") == false) && (top1.equalsIgnoreCase("jalapeno slice") == false))
            {
                System.out.println("Please enter either 'Prawn' or 'Jalapeno Slice' (case insensitive).");
            }
            else
            {
                validTop = true;
            }
        }while(!validTop);
        return top1;
    }

    /**
     * method to input second topping
     * @return second pizza topping type
     */
    public String inputTop2()
    {
        String top2;
        boolean validTop2 = false;
        do
        {
            System.out.println("\nEnter Topping 2 (Prawn / Jalapeno Slice) [4 pieces]: ");
            top2 = pizzaInputs.getInputString();
            if((top2.equalsIgnoreCase("prawn") == false) && (top2.equalsIgnoreCase("jalapeno slice") == false))
            {
                System.out.println("Please enter either 'Prawn' or 'Jalapeno Slice' (case insensitive).");
            }
            else
            {
                validTop2 = true;
            }
        }while(!validTop2);
        return top2;
    }

    //END OF USER INPUT METHODS
}
