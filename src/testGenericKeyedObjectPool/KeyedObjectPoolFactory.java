package testGenericKeyedObjectPool;

import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;
/**
 * @author exinmia
 * @since 2014
 *
 */
public class KeyedObjectPoolFactory extends BaseKeyedPoolableObjectFactory<KeyObject, ValueObject> {
    /* (non-Javadoc)
     * @see org.apache.commons.pool.BaseKeyedPoolableObjectFactory#makeObject(java.lang.Object)
     */
    @Override
    public ValueObject makeObject(KeyObject key) throws Exception {
        ValueObject obj = new ValueObject();
        obj.key = key;
        obj.id = System.currentTimeMillis();
        return obj;
    }

}
