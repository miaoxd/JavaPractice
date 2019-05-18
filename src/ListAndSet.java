/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2013 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

/**
 * @author exinmia
 * @since 2013
 *
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ListAndSet {

    public static void main(String[] args)
    {
////        long
        System.out.println("Compare the performance of set and list on contains() operation.");
        List<Long> list = new ArrayList<Long>();
        for(long i=0;i<900000;i++)
        {
            list.add(UUID.randomUUID().getMostSignificantBits());
        }
        
        long start = System.nanoTime();
        list.contains(4000000);
        System.out.println("list: "+ (System.nanoTime() - start));
        
        Set<Long> set = new HashSet<Long>();
        for(long i=0;i<900000;i++)
        {
            set.add(UUID.randomUUID().getMostSignificantBits());
        }
        
        start = System.nanoTime();
        set.contains(4000000);
        System.out.println("set: "+ (System.nanoTime() - start));
        
        
        
        List<String> listStr = new ArrayList<String>();
        for(long i=0;i<90000;i++)
        {
            listStr.add("abcd"+UUID.randomUUID().getMostSignificantBits());
        }
        
        start = System.nanoTime();
        listStr.contains("abcd"+4000000);
        System.out.println("list for String: "+ (System.nanoTime() - start));
        
        Set<String> setStr = new HashSet<String>();
        for(long i=0;i<90000;i++)
        {
            setStr.add("abcd"+UUID.randomUUID().getMostSignificantBits());
        }
        
        start = System.nanoTime();
        setStr.contains("abcd"+4000000);
        System.out.println("set for String: "+ (System.nanoTime() - start));
        
        System.out.println("set is better than list");
        
    }
}