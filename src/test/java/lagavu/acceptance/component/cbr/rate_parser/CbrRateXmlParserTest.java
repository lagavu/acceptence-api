package lagavu.acceptance.component.cbr.rate_parser;

import lagavu.acceptance.component.cbr.rate_parser.exception.CbrRateParserException;
import lagavu.acceptance.component.cbr.rate_parser.identifier.RateIdentifier;
import lagavu.acceptance.component.cbr.rate_parser.parser.CbrRateXmlParser;
import lagavu.acceptance.data_fixture.cbr.CbrRateApiMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CbrRateXmlParserTest {

    private CbrRateXmlParser cbrRateXmlParser;

    @BeforeEach
    void setUp() {
        this.cbrRateXmlParser = new CbrRateXmlParser();
    }

    @Test
    void successfulXmlParsingUsdRate() {
        Float usdRate = cbrRateXmlParser.parse(CbrRateApiMock.getXmlRates(), RateIdentifier.USD.getIdentifier());
        Float expectedRate = (float) 73.9866;

        Assertions.assertEquals(expectedRate, usdRate);
    }

    @Test
    public void getExceptionWhenParseIncorrectRateOfIdentifier() {
        Exception exception = Assertions.assertThrows(CbrRateParserException.class, () -> {
            cbrRateXmlParser.parse(CbrRateApiMock.getXmlRates(), "R0111111");
        });

        String expectedMessage = "Not found value rate for current identifier.";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}