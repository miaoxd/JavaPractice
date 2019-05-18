import java.lang.reflect.Field;

/**
 * @since 2015
 *
 */
public class EnumTest {
    
    public enum Enum {
        A("a"), B("b"),C("c");
        private String value;
        public String getValue(){
            return value;
        }
        public void setValue(String value){
            this.value = value;
        }
        Enum(String v){
            this.value = v;
        }
    }
    
    
    public enum MyEnum {
        A("Start There"),
        B("Start Here");
        private String value;
        public String getValue(){
            return value;
        }
        public void setValue(String value){
            this.value = value;
        }
        MyEnum(String name) {
            try {
                Field fieldName = getClass().getSuperclass().getDeclaredField("name");
                fieldName.setAccessible(true);
                fieldName.set(this, name);
                fieldName.setAccessible(false);
            } catch (Exception e) {}
        }
        
        public String getVal(){
            return value;
        }
        
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Enum.A);
        //System.out.println(Enum.valueOf("a"));
        System.out.println(Enum.valueOf("A"));
        System.out.println(Enum.A.getValue());
        
        System.out.println(MyEnum.valueOf("Start Here").getVal());

    }

}
