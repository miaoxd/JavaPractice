import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author exinmia
 * @since 2013
 *
 */
public class UTC2Local {
    public static void main(String[] args) {
        Date date = new Date(135855646060L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss.SSS");
        System.out.println(sdf.format(date));
    }
}
