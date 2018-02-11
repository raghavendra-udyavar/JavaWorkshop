import controller.deserializer.PriceUpdateDeserializer;

class ExchangeRate
{
    public static void main(String[] args){
        System.out.println("Hello! World");

        String input = "Hello! World";
        PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();
        priceUpdateDeserializer.Deserialize(input);

        System.out.println("Done!!");
    }
}

