import org.apache.commons.numbers.fraction.Fraction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
//        HttpRequest request = null;
//        try {
//            request = HttpRequest.newBuilder()
//                    .uri(new URI("https://java.net/website/java-champions/bios.html"))
//                    .version(HttpClient.Version.HTTP_2)
//                    .GET()
//                    .build();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        HttpResponse<String> response = null;
//        try {
//             response = HttpClient.newBuilder().build()
//                    .send(request, HttpResponse.BodyHandlers.ofString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(response.body().toString());
        //https://www.boblechef.com/recettes/dahl-de-lentilles-rouges -> OK
        //https://www.ricardocuisine.com/en/recipes/5312-ratatouille -> NOK(EN)
        //https://www.ricardocuisine.com/recettes/5312-ratatouille
//        Document document = Jsoup.connect("https://www.ricardocuisine.com/recettes/5312-ratatouille").timeout(0).get();
//        Document document = Jsoup.connect("https://www.boblechef.com/recettes/dahl-de-lentilles-rouges")
//                .timeout(0).get();
//        Document document = Jsoup.connect(" https://www.loveandlemons.com/homemade-pasta-recipe/")
//                .timeout(0).get();
//        Document document = Jsoup.connect("https://tasty.co/recipe/pizza-dough")
//                .timeout(0).get();

        String url1 = "https://www.livewellbakeoften.com/scone-recipe/#recipe";
        String url2 = "https://tastesbetterfromscratch.com/easy-tiramisu/";
        String url3="https://www.recipetineats.com/apple-crumble/";
        String url4="https://www.aspicyperspective.com/best-hamburger-patty-recipe/";
        String url5="https://www.parsehub.com/blog/html-scraping/";
        String url6="https://www.ambitiouskitchen.com/dal-recipe/";
        //String url7="https://www.loveandlemons.com/homemade-pasta-recipe/"; //case not covered
        String url7="https://biancazapatka.com/en/red-lentil-dahl/";
        String url8="https://www.noracooks.com/red-lentil-dahl/";
        Document document = Jsoup.connect(url1).timeout(0).get();



       Node result = method3(document);
       System.out.println(result);
       System.out.println(result.childNodeSize());
       for (int index = 0; index<result.childNodeSize(); index++)
       {
           Node element = result.childNode(index);
           System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+element.childNodeSize());
//           System.out.println(element.childNodes());
           for(Node node : element.childNodes())
           {
               var atts = node.attributes();
               if(node instanceof Element)
               {
                   Element elm = (Element)node;
                   System.out.println("DATA@=2");
                   System.out.println(elm.tagName("span").ownText());
               }

//               for(Attribute att : atts)
//               {
//                   Element elm = (Element)node;
//                   System.out.println("DATA@=2");
//                   System.out.println(att.toString());
//                   System.out.println(att.getKey());
//                   System.out.println(elm.tagName("span").ownText());
//
//               }
           }
       }

        //Use graph theory with LCA




    }

    public static void method2(Document document)
    {
        Elements elements = document.getAllElements();
        for (Element element : elements)
        {
            if (element.ownText().contains("cups"))
            {
                System.out.println(element.ownText());
            }
        }
    }
    public static void method1(Document document)
    {
        Elements elements = document.getAllElements();


        for (Element element : elements)
        {
            System.out.println(element.ownText());
            if(element.ownText().contains("Ingredients")) {
                System.out.println(element.ownText());
                // La liste d'ingredients semblent etre dans parentNode

//                List<Node> nodes = element.parentNode().childNodes();
//                for (Node node : nodes)
//                {
//                    if(node instanceof Element)
//                    {
//                        Element newElement = (Element)node;
//                        if(newElement!=null)
//                        {
//                            System.out.println(newElement.ownText());
//
//                        }
//                    }
//
//
//                }
            }


        }

        Elements allElements =
                document.getElementsByTag("h3");
//        for (Element element : allElements) {
//
//            System.out.println(
//                    element.ownText());
////            if ("New!".equals(
////                    element.nextElementSibling()!=null
////                            ? element.nextElementSibling()
////                            .ownText()
////                            : "")) {
////                System.out.println(
////                        element.ownText());
////            }
//        }
    }

    public static Node method3(Document document) {
        Elements elements = document.getAllElements();
        var elementIt = elements.iterator();
//                for (Element element : elements) {
//
//
//                    String data =element.ownText();
//                    System.out.println(data);
//                }
        int case1 = 0;
        int case2 = 0;
        int index = 0;
        int lastindex = 0;

        while (elementIt.hasNext()){

            var element = elementIt.next();
            Element elmentCase2 = null;

            String data =element.ownText();
            System.out.println(data);
            List<String> splitData = List.of(data.split(" "));
            if(splitData.size()>=2) {
                if (isNumeric(splitData.get(0)) || splitData.get(0).contains("/") && isNumeric(splitData.get(1)) || splitData.get(1).contains("/") )
                {
                    var elementToDefine = elementIt.next();
                    String dataToDefine =elementToDefine.ownText();
                    System.out.println(dataToDefine);
                    List<String> splitDataToDefine = List.of(dataToDefine.split(" "));
                    if (isIngredientUnit(splitDataToDefine.get(0)))
                    {
                        System.out.println("*************************case2: "+elementToDefine.ownText());
                        System.out.println(splitDataToDefine.get(0));
                        System.out.println("*************************");
                        elmentCase2 = elementToDefine;
                        case2++;
                    }
                }
                else if (isNumeric(splitData.get(0)) || splitData.get(0).contains("/"))
                {
                    if(isIngredientUnit(splitData.get(1))) {
                        System.out.println("------------------------------------case1 number: "+element.ownText());
                        System.out.println(data);
                        System.out.println("------------------------------------");
                        //break;
                        case1++;
                    }
                }

            }
            else if(splitData.size() == 1 && (isNumeric(splitData.get(0)) || splitData.get(0).contains("/")))
            {
                var elementToDefine = elementIt.next();
                String dataToDefine =elementToDefine.ownText();
                System.out.println(dataToDefine);
                List<String> splitDataToDefine = List.of(dataToDefine.split(" "));
                if (isIngredientUnit(splitDataToDefine.get(0)))
                {
                    System.out.println("*************************case2: "+elementToDefine.ownText());
                    System.out.println(splitDataToDefine.get(0));
                    System.out.println("*************************");
                    elmentCase2 = elementToDefine;
                    case2++;
                }

            }
            if(case1 == 2)
            {
                var parentNode = element.parentNode();
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!case1: "+case1);
                System.out.println(parentNode.toString());
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!case1: "+case1);
                return parentNode.parentNode();

            }
            else if(case2 == 2)
            {
                var parentNode = elmentCase2.parentNode();

                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!case2: "+case2);
                System.out.println(parentNode.parentNode().toString());
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!cas2: "+case2);
                //extraction

                System.out.println(parentNode.parentNode().childNode(1));
                return parentNode.parentNode();

            }

        }
        return null;
    }

    private static boolean isNumeric(String data)
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

    private static boolean isIngredientUnit(String data)
    {
        if (data.contains("cup") || data.contains("cups") || data.contains("teaspoon") || data.contains("teaspoons")
        || data.contains("tablespoon") || data.contains("tablespoons"))
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

    private double getNumberFromStringFraction(String fraction)
    {
        Fraction result = Fraction.parse(fraction);
        return result.doubleValue();
    }
}

