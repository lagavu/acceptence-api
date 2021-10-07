package lagavu.acceptance.component.cbr.cbr_exchange_rate_client.util.date_formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CbrDateFormatter {

    public static final String DATE_FORMAT_dd_MM_yyyy = "dd-MM-yyyy";

    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_dd_MM_yyyy);
        Date date = new Date();

        return formatter.format(date);
    }
}
