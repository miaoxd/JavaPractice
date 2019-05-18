import java.util.ArrayList;
import java.util.List;

/**
 * @since 2015
 *
 */
public class ListElementUpdate {
    public ListElementUpdate(){
        
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Elem> lst = new ArrayList<Elem>();
        Elem elem = new Elem();
        elem.setValue("aaa");
        lst.add(elem);

        System.out.println(lst.get(0).getValue());
        lst.get(0).setValue("bbb");
        System.out.println(lst.get(0).getValue());
        Elem e = lst.get(0);
        e.setValue("ccc");
        System.out.println(lst.get(0).getValue());
    }

}
