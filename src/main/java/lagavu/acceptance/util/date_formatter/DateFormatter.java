package lagavu.acceptance.util.date_formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static String getCurrentDate(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = new Date();

        return formatter.format(date);
    }
}
