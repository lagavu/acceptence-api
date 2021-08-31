package lagavu.acceptance.util.document_creator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

class DocumentCreatorTest {

    @Test
    void createFromXmlString() {
        Document document = DocumentCreator.createFromXmlString("<?xml version=\"1.0\" encoding=\"windows-1251\"?><ValCurs>test</ValCurs>");

        Assertions.assertNotNull(document);
        Assertions.assertEquals("test", document.getFirstChild().getTextContent());
    }
}