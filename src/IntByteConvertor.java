
/**
 * @since 2013
 *
 */
public class IntByteConvertor {
    public static byte[] int2Byte(int intValue) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (intValue >> 8 * (3 - i) & 0xFF);
            //System.out.print(Integer.toBinaryString(b[i])+" ");
            //System.out.print((b[i]& 0xFF)+" ");
        }
        return b;
    }

    public static int byte2Int(byte[] b) {
        int intValue = 0;
        for (int i = 0; i < b.length; i++) {
            intValue += (b[i] & 0xFF) << (8 * (3 - i));
            //System.out.print(Integer.toBinaryString(intValue)+" ");
        }
        return intValue;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(byte2Int(int2Byte(1000)));

    }

}
