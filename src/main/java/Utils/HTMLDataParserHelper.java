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
    //TODO remplacer par regx peut-etre mieux ?
    public static boolean isIngredientUnit(String data)
    {
        if (data.contains("cup") || data.contains("cups"))
        {
            return true;
        }
        else if(data.equalsIgnoreCase("g") || data.equalsIgnoreCase("kg") || data.equalsIgnoreCase("Oz") || data.equalsIgnoreCase("ml") || data.equalsIgnoreCase("L") || data.equalsIgnoreCase("lb"))
        {
            return true;
        }
        else if(data.contains("grams") || data.contains("kilograms") || data.contains("Oz") || data.contains("mililiters") || data.contains("liters") || data.contains("pound"))
        {
            return true;
        }
        else
        {
            return false;
        }

    }


}
