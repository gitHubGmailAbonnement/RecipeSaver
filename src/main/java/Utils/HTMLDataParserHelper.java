package Utils;

public class HTMLDataParserHelper {

    public static boolean isNumeric(String data)
    {
        try {
            Double.parseDouble(data);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    public static boolean isNumericFranction(String data)
    {
        boolean result=false;
        if(data.contains("/"))
        {
            var fracntionNumbers = data.split("/");
            for (String number : fracntionNumbers)
            {
                try {
                    Double.parseDouble(number);
                    result = true;
                }
                catch (NumberFormatException e)
                {
                    return false;
                }
            }
        }
        return result;
    }
    //TODO remplacer par regx peut-etre mieux ?
    public static boolean isIngredientUnit(String data)
    {
        if (data.contains("cup") || data.contains("cups") || data.equals("tsp") || data.equals("tbsp"))
        {
            return true;
        }
        else if(data.equalsIgnoreCase("g") || data.equalsIgnoreCase("kg") || data.equalsIgnoreCase("Oz") || data.equalsIgnoreCase("ml") || data.equalsIgnoreCase("L") || data.equalsIgnoreCase("lb"))
        {
            return true;
        }
        else return data.contains("grams") || data.contains("kilograms") || data.contains("Oz") || data.contains("mililiters") || data.contains("liters") || data.contains("pound");

    }


}
