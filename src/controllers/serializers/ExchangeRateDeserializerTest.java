package controllers.serializers;

import controllers.calculator.ExchangeRateCalculator;
import controllers.exceptions.EmptyInputException;
import models.structures.ExchangeRateRequest;
import models.tables.EdgeWeightedDigraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateDeserializerTest {

    String input = "EXCHANGE_RATE_REQUEST DBS USD SBI INR";

    EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph();



    @Test
    void deserialize() {
    }

    @Test
    void isInputNullOrEmpty() {

        assertThrows(EmptyInputException.class, () ->{
            String currInput = "";
            ExchangeRateCalculator exchangeRateCalculator = new ExchangeRateCalculator(edgeWeightedDigraph);
            ExchangeRateRequestDeserializer exchangeRateRequestDeserializer = new ExchangeRateRequestDeserializer(edgeWeightedDigraph, exchangeRateCalculator);
            exchangeRateRequestDeserializer.isInputNullOrEmpty(currInput);
        }, "empty input exception");
    }

    @Test
    void checkInputLength(){
        ExchangeRateCalculator exchangeRateCalculator = new ExchangeRateCalculator(edgeWeightedDigraph);
        ExchangeRateRequestDeserializer exchangeRateRequestDeserializer = new ExchangeRateRequestDeserializer(edgeWeightedDigraph, exchangeRateCalculator);
        String[] requestStrings = exchangeRateRequestDeserializer.breakInput(input);
        assertEquals(ExchangeRateRequestDeserializer.EXCHANGERATEINPUTCOUNT, requestStrings.length);
    }
}