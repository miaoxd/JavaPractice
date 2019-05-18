import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * @since 2015
 *
 */
public class TestClass {

    private String val;
    private Map<String, String> mapToBeUpdatedByOtherInstance = new HashMap<String, String>();
    private Map<String, String> mapNotImpactByOtherInstance = new HashMap<String, String>();
    
    public Map<String, String> getMapNotImpactByOtherInstance() {
        return mapNotImpactByOtherInstance;
    }
    public void setMapNotImpactByOtherInstance(Map<String, String> mapNotImpactByOtherInstance) {
        this.mapNotImpactByOtherInstance = mapNotImpactByOtherInstance;
    }
    public Map<String, String> getMapToBeUpdatedByOtherInstance() {
        return mapToBeUpdatedByOtherInstance;
    }
    public void setMapToBeUpdatedByOtherInstance(Map<String, String> mapToBeUpdatedByOtherInstance) {
        this.mapToBeUpdatedByOtherInstance = mapToBeUpdatedByOtherInstance;
    }
    public TestClass(){
        
    }
    public TestClass(String v){
        this.val = v;
        this.mapToBeUpdatedByOtherInstance.put(v, v);
    }
    public TestClass(TestClass t){
        this.val = t.val;
        
        //the map will only be referenced 
        this.mapToBeUpdatedByOtherInstance = t.mapToBeUpdatedByOtherInstance;
        
        //the map will be value copied to a new map
        mapNotImpactByOtherInstance = new HashMap<String,String>(t.getMapNotImpactByOtherInstance());
    }
    public String getVal() {
        return val;
    }
    public void setVal(String val) {
        this.val = val;
        this.mapToBeUpdatedByOtherInstance.put(val, val);
        this.mapNotImpactByOtherInstance.put(val, val);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        TestClass x = new TestClass("x");
        TestClass y = new TestClass(x);
        TestClass z = new TestClass(x);
        y.setVal("y");
        z.setVal("z");
        
        y.getMapToBeUpdatedByOtherInstance().remove("x");
        z.getMapNotImpactByOtherInstance().remove("y");
        
        System.out.println(x.getVal());
        System.out.println(x.getMapToBeUpdatedByOtherInstance());
        System.out.println(x.getMapNotImpactByOtherInstance());
        System.out.println(y.getVal());
        System.out.println(y.getMapToBeUpdatedByOtherInstance());
        System.out.println(y.getMapNotImpactByOtherInstance());
        System.out.println(z.getVal());
        System.out.println(z.getMapToBeUpdatedByOtherInstance());
        System.out.println(z.getMapNotImpactByOtherInstance());

    }

}
