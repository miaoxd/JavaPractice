import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;


public class Hello {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    
	    System.out.println(Byte.SIZE);
	    System.out.println(Byte.MAX_VALUE);
	    System.out.println(Byte.MIN_VALUE);
	    
	    System.out.println(Short.MAX_VALUE);
	    //System.out.println(new Short("60000"));
	    
	    String sss = "1111222233334444";
	    sss.replace("22", "aa");
	    System.out.println(sss);
	    sss = sss.replace("22", "aa");
	    System.out.println(sss);
	    Date date = new Date(1392950562880L);//136132317000000000
	    System.out.println(date.getTime());
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss.SSS");
	    System.out.println(sdf.format(date));
	    
	    
	    System.out.println("Short.MAX_VALUE" + Short.MAX_VALUE);
	    System.out.println("Short.MIN_VALUE" + Short.MIN_VALUE);
	    
	    System.out.println("Integer.MAX_VALUE" + Integer.MAX_VALUE);
	    System.out.println("Integer.MIN_VALUE" + Integer.MIN_VALUE);
	    
	       System.out.println("Long.MAX_VALUE" + Long.MAX_VALUE);
	            System.out.println("Long.MIN_VALUE" + Long.MIN_VALUE);
	            
	    System.out.println(new Date().getTime());
	    
	    for (int i=0; i<10; i++){
	        System.out.println(i%3);
	    }
	    
	    Map<Date, String> mmm = new HashMap<Date, String>();
	    mmm.put(new Date(), "hello1");
	    
	    for (Entry<Date, String> s: mmm.entrySet()){
	        System.out.println(s.getKey());
	        System.out.println(s.getValue());
	    }
	    try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
	    Map<Date, String> mmm2 = new HashMap<Date, String>();
	    mmm2.put(new Date(), "hello2");
	    
	    mmm = mmm2;
            for (Entry<Date, String> s: mmm.entrySet()){
                System.out.println(s.getKey());
                System.out.println(s.getValue());
            }	    
	    
//		System.out.println("SubNetwork=ONRM_ROOT_MO_R,SubNetwork=SPMLTE1,MeContext=LTE100ERBS00050");
//		System.out.print("hashCode(): ");
//		System.out.println("SubNetwork=ONRM_ROOT_MO_R,SubNetwork=SPMLTE1,MeContext=LTE100ERBS00050".hashCode());
//		System.out.print("Dir num: ");
//		System.out.println(Math.abs("SubNetwork=ONRM_ROOT_MO_R,SubNetwork=SPMLTE1,MeContext=LTE100ERBS00050".hashCode() % 50));
//
//		System.out.println("SubNetwork=ONRM_ROOT_MO_R,SubNetwork=NETSim04,MeContext=LTE13ERBS00002");
//		System.out.print("hashCode(): ");
//		System.out.println("SubNetwork=ONRM_ROOT_MO_R,SubNetwork=NETSim04,MeContext=LTE13ERBS00002".hashCode());
//		System.out.print("Dir num: ");
//		System.out.println(Math.abs("SubNetwork=ONRM_ROOT_MO_R,SubNetwork=NETSim04,MeContext=LTE13ERBS00002".hashCode() % 50));
//
//		System.out.println("SubNetwork=ONRM_ROOT_MO_R,SubNetwork=NETSim04,MeContext=LTE13ERBS00003");
//		System.out.print("hashCode(): ");
//		System.out.println("SubNetwork=ONRM_ROOT_MO_R,SubNetwork=NETSim04,MeContext=LTE13ERBS00003".hashCode());
//		System.out.print("Dir num: ");
//		System.out.println(Math.abs("SubNetwork=ONRM_ROOT_MO_R,SubNetwork=NETSim04,MeContext=LTE13ERBS00003".hashCode() % 50));


		System.out.println("SubNetwork=ONRM_ROOT_MO_R,SubNetwork=SPMLTE5,MeContext=LTE104ERBS00081");
		System.out.print("hashCode(): ");
		System.out.println("SubNetwork=ONRM_ROOT_MO_R,SubNetwork=SPMLTE5,MeContext=LTE104ERBS00081".hashCode());
		System.out.print("Dir num: ");
		System.out.println(Math.abs("SubNetwork=ONRM_ROOT_MO_R,SubNetwork=SPMLTE5,MeContext=LTE104ERBS00081".hashCode() % 50)+1);
	
		System.out.println("Integer.MAX_VALUE " + Integer.MAX_VALUE);
		System.out.println("Long.MAX_VALUE " + Long.MAX_VALUE);
		System.out.println(String.format("2^64 Math.pow(2, 64) (double, 17bit precision): %20.0f",Math.pow(2, 64)));
		
		BigDecimal n = new BigDecimal(2);
		
		System.out.println("2^32 n.pow(32) (BigDecimal): " + n.pow(32));
		System.out.println("2^64 n.pow(64) (BigDecimal): " + n.pow(64));
		
		
		System.out.println(" " + n.pow(64));
		System.out.println("-" + Long.MAX_VALUE);
		System.out.println("_________________________________");
		System.out.println(" "+n.pow(64).subtract(new BigDecimal(Long.MAX_VALUE)));
		
		
		System.out.println("2^64 n.pow(64).pow(64) (BigDecimal): " + n.pow(64).pow(64));
		
		
		
                final BigDecimal pmPdcpVolDlDrbTrans = new BigDecimal("1000000");
                final BigDecimal pmPdcpPktReceivedDl = new BigDecimal("2000000");

                System.out.println(pmPdcpVolDlDrbTrans.multiply(new BigDecimal(1000)).divide(new BigDecimal(8))
                        .add(pmPdcpPktReceivedDl.multiply(new BigDecimal(8))).divide(new BigDecimal(1000000)));
		
		BigDecimal sum = new BigDecimal("0");
		sum = sum.add(pmPdcpVolDlDrbTrans.multiply(new BigDecimal(1000)).divide(new BigDecimal(8))
                        .add(pmPdcpPktReceivedDl.multiply(new BigDecimal(8))).divide(new BigDecimal(1000000)));
		System.out.println(sum);

	        sum.setScale(20);
		sum = new BigDecimal("101.123456789");

		DecimalFormat formater = new DecimalFormat("#0.####");
		System.out.println(formater.format(sum));
		
		System.out.println(sum.setScale(2, BigDecimal.ROUND_HALF_UP));
		System.out.println(sum.setScale(6, BigDecimal.ROUND_HALF_UP));
		System.out.println(sum.setScale(8, BigDecimal.ROUND_HALF_UP).toString());
		System.out.println(sum.setScale(60, BigDecimal.ROUND_HALF_UP));
		
		System.out.println( (new BigDecimal(2)).pow(64));
		
		
                BigDecimal counter1 = new BigDecimal("77");
                BigDecimal counter2 = new BigDecimal("79");

                BigDecimal ret1 = counter1.add(counter2).divide(new BigDecimal(1000000));
                System.out.println(ret1);
                
                counter1 = new BigDecimal("10000076");
                counter2 = new BigDecimal("10000078");
                BigDecimal ret2 = counter1.add(counter2).divide(new BigDecimal(1000000));
                
                System.out.println(ret2);
                
                System.out.println(ret2.subtract(ret1));
		
                System.out.println(String.format("%0,3d", 2));
                
                System.out.println(String.format("%0,3d", (new Date().getTime())%1000));
                
                System.out.println(String.format("%,6f", 2323.13423546456));
                
/////////////
//following shows that search and substring is faster than split.
//
                String str = "4130421:A3:20120914222514401:35:39:4:6:40060:21:NIL:NIL:NIL:NIL:NIL:NIL:NIL";
                String s = str;
                StringBuilder sb = new StringBuilder("4130421:A3:20120914222514401:35:39:4:6:40060:21:NIL:NIL:NIL:NIL:NIL:NIL:NIL");
                System.out.println("test performance on the string split");
                System.out.println("creating string");
                long start = System.nanoTime();

                for (int i=0; i<1000; i++){
                    //sb.append(";").append(str);
                    s += ";"+str;
                }
                long end = System.nanoTime();
                System.out.println("Time escaped (nano seconds): " + (end-start));
                System.out.println("spliting..");
                start = System.nanoTime();
                String[] ss = s.split(";");
                end = System.nanoTime();
                
                System.out.println("Time escaped (nano seconds): " + (end-start));
                
                int endPos = -1; 
                int startPos = 0;
                start = System.nanoTime();
                endPos = s.indexOf(';', endPos+1);
                while (endPos>0){
                    String subs = s.substring(startPos, endPos);
                    //System.out.print(subs.length() + " ");
                    
                    startPos = endPos;
                    endPos = s.indexOf(';', endPos+1);
                }
                end = System.nanoTime();
                System.out.println("Time escaped (nano seconds): " + (end-start));
///////////////////
                
                
                
                final StringTokenizer tokenizer = new StringTokenizer("abc/def/lala", "/");
                while (tokenizer.hasMoreTokens()) {
                    tokenizer.nextToken();
                    if (!tokenizer.hasMoreTokens()){
                    System.out.println(tokenizer.nextToken());
                    }
                    else{
                        
                    }
                }

                
	}

}
