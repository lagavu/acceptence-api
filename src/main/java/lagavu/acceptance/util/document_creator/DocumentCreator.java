package lagavu.acceptance.util.document_creator;

import lagavu.acceptance.util.document_creator.exception.DocumentCreatorException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class DocumentCreator {

    public static Document createFromXmlString(String content) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(content));
            return documentBuilder.parse(inputSource);
        } catch (ParserConfigurationException | IOException | SAXException exception) {
            throw new DocumentCreatorException(exception.getMessage());
        }
    }
}
