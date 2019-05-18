import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @since 2015
 *
 */
public class testRegex {
    public static void main(String[] args) {
        String s = "/m/t/wd/nl/n/p/m/wd/nl/n/p/m/wd/nl/n/p/m/v/n";
        Pattern p = Pattern.compile("/m/t.*?/nl/n/p/m");
        Matcher mm = p.matcher(s);
        //output 0, 16. here .*?  «√„«ø∆•≈‰
        while (mm.find()) {
            System.out.println(mm.group());
            System.out.println(mm.start());
            System.out.println(mm.end());
        }

        p = Pattern.compile("/m/t.*/nl/n/p/m");
        mm = p.matcher(s);
        //output: 0, 40£¨ here .*  «Ã∞¿∑∆•≈‰
        while (mm.find()) {
            System.out.println(mm.group());
            System.out.println(mm.start());
            System.out.println(mm.end());
        }
    }

}
