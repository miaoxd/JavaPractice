
/**
 * @since 2014
 *
 */
public class OctetsString {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final String octs = "07:de:06:10:11:20:2f:09:2b:00:00";
        System.out.println(exchange(octs));
    }
    
    
    private static String exchange(String input) {
        int number = (input.length() + 1) / 3;
        String strReturn = "";
        String[] strArray = input.split(":");
        byte[] byteArray = new byte[2];
        byte[] retByte = new byte[strArray.length];
        int intTemp;
        String c;
        
        for (int i = 0; i < strArray.length; i++ ) {
            
            System.out.println(String.valueOf(strArray[i]));
        }
        return strReturn;
    }

}
