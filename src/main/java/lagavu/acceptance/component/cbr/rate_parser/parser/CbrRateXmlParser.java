package lagavu.acceptance.component.cbr.rate_parser.parser;

import lagavu.acceptance.component.cbr.rate_parser.ICbrRateParser;
import lagavu.acceptance.component.cbr.rate_parser.exception.CbrRateParserException;
import lagavu.acceptance.component.cbr.rate_parser.util.document_creator.DocumentCreator;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.xpath.*;

@Component
public class CbrRateXmlParser implements ICbrRateParser {

    @Override
    public Float parse(String rates, String rateIdentifier) {
        Document document = DocumentCreator.createFromXmlString(rates);
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        try {
            XPathExpression xPathExpression = xPath.compile(getExpression(rateIdentifier));
            NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
            return Float.parseFloat(getRate(nodeList));
        } catch (XPathExpressionException xPathExpressionException) {
            throw new CbrRateParserException(xPathExpressionException.toString());
        }
    }

    private static String getExpression(String rateIdentifier) {
        return String.format("/ValCurs/Valute[@ID='%s']/Value", rateIdentifier);
    }

    private static String getRate(NodeList nodeList) throws CbrRateParserException {
        if (nodeList.getLength() == 0) {
            throw new CbrRateParserException("Not found value rate for current identifier.");
        }
        return nodeList.item(0).getTextContent().replace(',', '.');
    }
}
