/**
 * Class to operate on a Pizza to claculate its price.
 * @author 850844 
 */
public class CalculatePrice
{
    private Pizza p;
    
    /**
     * Price calculation constructor
     * @param pToCalc The pizza which is to be calculated on.
     */
    public CalculatePrice(Pizza pToCalc)
    {
        p = pToCalc;
    }
    
    /**
     * Method to calculate a Pizza's total price
     * @return pizza total price
     */
    public double returnPizzaTotal()
    {
        double total = 0;
        total += returnBasePrice(p.getSize(), p.getCrust());
        total += returnSaucePrice(p.getSauce());
        total += returnToppingPrice(p.getTop1(), 1);
        total += returnToppingPrice(p.getTop2(), 2);
        
        return total;
    }
    
    /**
     * Method to return the price of a pizza's base
     * @param diameter pizza diameter size
     * @param base pizza base type
     * @return pizza base price
     */
    public double returnBasePrice(int diameter, String base)
    {
        double radius = diameter/2;
        if(base.equalsIgnoreCase("Deep Pan"))
        {
            double area = Math.PI * Math.pow(radius, 2);
            double price = area * 11;
            return price;
        }
        else if(base.equalsIgnoreCase("Thin Crust"))
        {
            double area = Math.PI * Math.pow(radius, 2);
            double price = area * 8;
            return price;
        }
        else
        {
            double area = Math.PI * Math.pow(radius, 2);
            double price = area * 14;
            return price;
        }
    }
    
    /**
     * Method to return the price of the sauce used on a pizza
     * @param sauce sauce type
     * @return price of sauce used
     */
    public double returnSaucePrice(String sauce)
    {
        if(sauce.equalsIgnoreCase("BBQ"))
        {
            return 50;
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * Method to return the Price of one of the toppings used on a pizza
     * @param topping the topping type
     * @param topNum whether the topping is the first of second entered (1 or 2 respectively)
     * @return the price for one of the used toppings
     */
    public double returnToppingPrice(String topping, int topNum)
    {
        if(topping.equalsIgnoreCase("Prawn"))
        {
            double prawnPrice;
            if(topNum == 1)
            {
                prawnPrice = 5 * 6;
                return prawnPrice;
            }
            else
            {
                prawnPrice = 4 * 6;
                return prawnPrice;
            }
        }
        else
        {
            double jalapenoPrice;
            if(topNum == 1)
            {
                jalapenoPrice = 5 * 4;
                return jalapenoPrice;
            }
            else
            {
                jalapenoPrice = 4 * 4;
                return jalapenoPrice;
            }
        }
    }
}
