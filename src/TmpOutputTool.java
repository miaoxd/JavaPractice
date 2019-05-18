
/**
 * @author exinmia
 * @since 2013
 *
 */
public class TmpOutputTool {

    /**
     * @param args
     */
    public static void main(String[] args) {
//            //LteScRTTDScopeMRO
//            for (int i=0; i<4096; i+=2){
//                System.out.println("\t\t\t\t\t\t<value>"+i+"</value>");
//            }
//            for (int i=4096; i<20472; i+=8){
//                System.out.println("\t\t\t\t\t\t<value>"+i+"</value>");
//            }
//            System.out.println("\t\t\t\t\t\t<value>"+20512.0000001+"</value>");
//            //LteScAOAScopeMRO
//            for (double i=0.0; i<360.0; i+=0.5){
//                System.out.println(String.format("\t\t\t\t\t\t<value>%.1f</value>",i));
//            }
//              //LteScPLRULQci
//              for (int i=0; i<20; i+=1){
//                  System.out.println("\t\t\t\t\t\t<value>"+i+"</value>");
//              }
//              for (int i=20; i<101; i+=5){
//                  System.out.println("\t\t\t\t\t\t<value>"+i+"</value>");
//              }      
//        //MR.LteScRIP
//        for (double i=-126; i<=-75.0; i+=0.1){
//            System.out.println(String.format("\t\t\t\t\t\t<value>%.1f</value>",i));
//        }
        //MR.PacketLossRateULQci
        for (int i=0; i<=20; i+=1){
            System.out.println(String.format("\t\t\t\t\t\t<value>%d</value>",i));
        }
        for (int i=25; i<=100; i+=5){
            System.out.println(String.format("\t\t\t\t\t\t<value>%d</value>",i));
        }
        
    }

}
