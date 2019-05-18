
/**
 * @since 2012
 *
 */
public class Crypt {

    private Crypt() {
    }

    static private int htod(final char c) {
        int ret;
        if (c >= '0' && c <= '9') {
            ret = c - '0';
        } else if (c >= 'A' && c <= 'Z') {
            ret = c - 'A' + 10;
        } else if (c >= 'a' && c <= 'z') {
            ret = c - 'a' + 10;
        } else {
            ret = -257;
        }

        return ret;
    }

    static public String decrypt(final String str1) {
        String str2 = "";

        if (str1.length() % 2 == 1) {
            return str2;
        }

        for (int i = str1.length(); i > 0; i -= 2) {
            final int temp = Crypt.htod(str1.charAt(i - 2)) * 16 + Crypt.htod(str1.charAt(i - 1));
            if (temp < 0) {
                return "";
            }

            char c = (char) temp;
            for (int j = 6; j >= 2; j--) {
                if (((str1.length() - i) / 2) % j == 0) {
                    c = (char) (c - j + 1);
                }
            }
            if (((str1.length() - i) / 2) % 2 == 0) {
                c = (char) (255 - c);
            }

            str2 = str2 + c;
        }

        //                  System.out.println( str2 );
        return str2;
    }

    static String dtoh(final int c) {
        final int high = c / 16;
        final int low = c % 16;

        String ret = "";
        if (high >= 0 && high <= 9) {
            ret = ret + high;
        } else if (high >= 10 && high <= 15) {
            ret = ret + (char) ('a' + high - 10);
        }

        if (low >= 0 && low <= 9) {
            ret = ret + low;
        } else if (low >= 10 && low <= 15) {
            ret = ret + (char) ('A' + low - 10);
        }

        return ret;
    }

    static public String encrypt(final String str1) {
        String str2 = "";
        for (int i = str1.length() - 1; i >= 0; i--) {
            char c = str1.charAt(i);
            if (i % 2 == 0) {
                c = (char) (255 - c);
            }

            for (int j = 2; j <= 6; j++) {
                if (i % j == 0) {
                    c = (char) (c + j - 1);
                }
            }

            //******  trace c to hex *********
            str2 = str2 + Crypt.dtoh(c);
        }

        return str2;
    }

    public static void printUsage(){
        System.out.println("-d <encrypted_pwd>");
        System.out.println("-e <pwd_to_be_encrypted>");
    }
    public static void main(final String[] args) {
        if (args.length != 2){
            printUsage();
            return;
        }
        if (args[0].equalsIgnoreCase("-e")){
            System.out.println(Crypt.encrypt(args[1]));
        }
        else if (args[0].equalsIgnoreCase("-d")){
            System.out.println(Crypt.decrypt(args[1]));
        }
        else {
            printUsage();
        }
        
    }

}
