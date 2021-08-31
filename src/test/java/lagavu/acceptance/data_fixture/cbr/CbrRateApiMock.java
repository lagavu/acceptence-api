package lagavu.acceptance.data_fixture.cbr;

import lagavu.acceptance.data_fixture.cbr.exception.CbrRateApiMockException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CbrRateApiMock {

    public static String getXmlRates() {
        File file = new File(getPath(), "cbr-xml-usd-rate-response");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                break;
            }

            return line;
        } catch (IOException ioException) {
            throw new CbrRateApiMockException(ioException.getMessage());
        }
    }

    private static String getPath() {
        return System.getProperty("user.dir") + "/src/test/java/lagavu/acceptance/data_fixture/cbr/";
    }
}
