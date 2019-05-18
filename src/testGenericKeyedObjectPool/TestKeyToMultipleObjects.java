package testGenericKeyedObjectPool;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * test result:
 * 1) key are identified using their hashcode.
 * 2) will not create new object if there is idle (e.g. the obj returned and the maxIdl>0).
 * 3) will create new object if there is no idle, for same key. So there may has multiple value objects for same key.
 * 4) maxActive is the max borrowed per key, not total
 * @author exinmia
 * @since 2014
 *
 */
public class TestKeyToMultipleObjects {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        TestKeyToMultipleObjects test = new TestKeyToMultipleObjects();
        System.out.println("test 1 ...");
        test.testIfCreateMultipleValueObjectForOneKeyWithoutReturnAfterBorrow();

        System.out.println("\n\ntest 2 ...");
        test.testIfCreateMultipleValueObjectForOneKeyWithReturnAfterBorrow();
        
        System.out.println("\n\ntest 3 ...");
        test.testMaxActive();
    }

    public boolean testIfCreateMultipleValueObjectForOneKeyWithoutReturnAfterBorrow() throws Exception {
        System.out.println("Test if one key will create more than one objects. Do not return after borrow.");

        KeyedObjectPoolFactory factory = new KeyedObjectPoolFactory();

        int MAX_ACTIVE = 4;
        int MAX_WAIT = 6000;
        int MAX_IDLE = 4;
        boolean testOnBorrow = true;
        boolean testOnReturn = true;

        GenericKeyedObjectPool<KeyObject, ValueObject> keyedObjPool = new GenericKeyedObjectPool<KeyObject, ValueObject>(
                factory, MAX_ACTIVE, GenericObjectPool.WHEN_EXHAUSTED_BLOCK, MAX_WAIT, MAX_IDLE, testOnBorrow,
                testOnReturn);

        KeyObject key1 = new KeyObject("host1", 1, "user1");

        ValueObject v1 = keyedObjPool.borrowObject(key1);

        System.out.println("1) first borrow: " + v1.id);
        Thread.sleep(1003);

        ValueObject v11 = keyedObjPool.borrowObject(key1);

        System.out.println("2) second borrow with same key object, and without return the first: " + v11.id);
        if (v11.id != v1.id) {
            System.out.println("\tnew value object created.");
        } else {
            System.out.println("\tNot create new object");
        }
        Thread.sleep(1003);

        KeyObject key2 = new KeyObject("host1", 1, "user1");

        System.out.println("key1.hashcode: " + key1.hashCode() + ", key2.hashcode: " + key2.hashCode());

        ValueObject v2 = keyedObjPool.borrowObject(key2);

        System.out
                .println("3) third borrow with new key object equals to the first key object, and without return the first: "
                        + v2.id);
        if (v2.id != v1.id) {
            System.out.println("\tnew value object created.");
        } else {
            System.out.println("\tNot create new object");
        }

        Thread.sleep(1003);

        ValueObject v22 = keyedObjPool.borrowObject(key2);

        System.out
                .println("4) fouth borrow with new key object equals to the first key object, and without return the first: "
                        + v22.id);
        if (v22.id != v2.id) {
            System.out.println("\tnew value object created.");
        } else {
            System.out.println("\tNot create new object");
        }

        return true;
    }

    /**
     * @return
     * @throws Exception
     */
    public boolean testIfCreateMultipleValueObjectForOneKeyWithReturnAfterBorrow() throws Exception {
        System.out.println("Test if one key will create more than one objects. Do return after borrow.");

        KeyedObjectPoolFactory factory = new KeyedObjectPoolFactory();

        int MAX_ACTIVE = 4;
        int MAX_WAIT = 6000;
        int MAX_IDLE = 4;
        boolean testOnBorrow = true;
        boolean testOnReturn = true;

        GenericKeyedObjectPool<KeyObject, ValueObject> keyedObjPool = new GenericKeyedObjectPool<KeyObject, ValueObject>(
                factory, MAX_ACTIVE, GenericObjectPool.WHEN_EXHAUSTED_BLOCK, MAX_WAIT, MAX_IDLE, testOnBorrow,
                testOnReturn);

        KeyObject key1 = new KeyObject("host1", 1, "user1");

        ValueObject v1 = keyedObjPool.borrowObject(key1);

        System.out.println("1) first borrow: " + v1.id);
        Thread.sleep(1003);
        keyedObjPool.returnObject(key1, v1);

        ValueObject v11 = keyedObjPool.borrowObject(key1);

        System.out.println("2) second borrow with same key object, and without return the first: " + v11.id);
        if (v11.id != v1.id) {
            System.out.println("\tnew value object created.");
        } else {
            System.out.println("\tNot create new object");
        }
        Thread.sleep(1003);
        keyedObjPool.returnObject(key1, v11);

        KeyObject key2 = new KeyObject("host1", 1, "user1");
        System.out.println("key1.hashcode: " + key1.hashCode() + ", key2.hashcode: " + key2.hashCode());
        ValueObject v2 = keyedObjPool.borrowObject(key2);

        System.out
                .println("3) third borrow with new key object equals to the first key object, and without return the first: "
                        + v2.id);
        if (v2.id != v1.id) {
            System.out.println("\tnew value object created.");
        } else {
            System.out.println("\tNot create new object");
        }

        Thread.sleep(1003);
        keyedObjPool.returnObject(key2, v2);

        ValueObject v22 = keyedObjPool.borrowObject(key2);

        System.out
                .println("4) fouth borrow with new key object equals to the first key object, and without return the first: "
                        + v22.id);
        if (v22.id != v2.id) {
            System.out.println("\tnew value object created.");
        } else {
            System.out.println("\tNot create new object");
        }

        return true;
    }
    
    
    public boolean testMaxActive() throws Exception {

        KeyedObjectPoolFactory factory = new KeyedObjectPoolFactory();

        int MAX_ACTIVE = 2;
        int MAX_WAIT = 6000;
        int MAX_IDLE = 1;
        boolean testOnBorrow = true;
        boolean testOnReturn = true;
        System.out.println("Test max active, ");

        GenericKeyedObjectPool<KeyObject, ValueObject> keyedObjPool = new GenericKeyedObjectPool<KeyObject, ValueObject>(
                factory, MAX_ACTIVE, GenericObjectPool.WHEN_EXHAUSTED_BLOCK, MAX_WAIT, MAX_IDLE, testOnBorrow,
                testOnReturn);

        KeyObject key1 = new KeyObject("host1", 1, "user1");

        ValueObject v1 = keyedObjPool.borrowObject(key1);

        System.out.println("1) first borrow: " + v1.id);
        Thread.sleep(1003);
        keyedObjPool.returnObject(key1, v1);

        KeyObject key2 = new KeyObject("host2", 2, "user2");
        ValueObject v2 = keyedObjPool.borrowObject(key2);

        System.out.println("2) second borrow with new key object not equals to the first key, and return the first: " + v2.id);
        Thread.sleep(1003);
        keyedObjPool.returnObject(key2, v2);
        

        KeyObject key3 = new KeyObject("host3", 3, "user3");

        ValueObject v3 = keyedObjPool.borrowObject(key3);

        System.out
                .println("3) third borrow with new key object not equals to the first and second key object, and return the second: "
                        + v3.id);
        Thread.sleep(1003);
        
        keyedObjPool.returnObject(key3, v3);
        KeyObject key4 = new KeyObject("host4", 4, "user4");
        ValueObject v4 = keyedObjPool.borrowObject(key4);

        System.out
                .println("4) fouth borrow with new key object not equals to the previous key object, and return the third: "
                        + v4.id);
        v1 = keyedObjPool.borrowObject(key1);
        System.out.println("5) fifth borrow with new key object equals to the first key object: "
                + v1.id);
        System.out.println("\tnow there are 1 borrowed");
        ValueObject v11 = keyedObjPool.borrowObject(key1);
        System.out.println("6) 6th borrow with first key, without return the first value obj: " + v11.id);
        System.out.println("\tnow there are 2 borrowed");
        try{
            v2 = keyedObjPool.borrowObject(key2);
            System.out.println("7) 7th borrow with second key, without return the prevous 2 value obj: " + v2.id);
            System.out.println("\tnow there are 3 borrowed");
        }
        catch (Exception e){
            System.out.println("\terror when borrow 3. " + e.getMessage());
        }
        try{
            v3 = keyedObjPool.borrowObject(key3);
            System.out.println("8) 8th borrow with third key, without return the prevous 3 value obj: " + v3.id);
            System.out.println("\tnow there are 4 borrowed");
        }
        catch (Exception e){
            System.out.println("\terror when borrow 4. " + e.getMessage());
        }
        try{
            ValueObject v12 = keyedObjPool.borrowObject(key1);
            System.out.println("9) 9th borrow key1, without return v1, v2, v3: " + v12.id);
            System.out.println("\tnow there are 5 borrowed");
        }
        catch (Exception e){
            System.out.println("\terror when borrow key1 for the 3rd time, without return the previous, the 3rd v1 obj. " + e.getMessage());
        }
        try{
            ValueObject v21 = keyedObjPool.borrowObject(key2);
            System.out.println("10) borrow key2, without return v2: " + v21.id);
        }
        catch (Exception e){
            System.out.println("\terror when borrow key2 for the 2nd time, without return the previous. " + e.getMessage());
        }
        try{
            ValueObject v22 = keyedObjPool.borrowObject(key2);
            System.out.println("11) borrow key2, without return v2: " + v22.id);
        }
        catch (Exception e){
            System.out.println("\terror when borrow key2 for the 3rd time, without return the previous. " + e.getMessage());
        }
        System.out.println("max total: " + keyedObjPool.getMaxTotal());
        return true;
    }

}
