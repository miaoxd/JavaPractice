import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
// * 用来测试map的赋值是否是原子操作，赋值与只读是否存在冲突。
// * 验证结果说明对map的赋值不会影响正在进行的map的遍历。
// * 
// * 主要出于对如下问题的解决：
// * 一个static map，有大量线程只读该map，有1个线程更新该map，如果对该map设置锁，则大量只读线程之间锁竞争可能会严重影响并发，降低性能。
// * 
// * 解决方案：
// * 更新map时，首先声明临时变量tmpMap，tmpMap填好值后，将其一步赋值给map： map=tmpMap
// * 
// * @since 2013
// *
// */
public class MapSyncAccess {
    static Map<String, String> map = new HashMap<String, String>();
    
    /**
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        //init map
        
        String s;
        File file = new File("output.txt");
        if (file.exists()){
            file.delete();
        }
        final RandomAccessFile raf = new RandomAccessFile(file, "rw");
        for (int i=0; i<30000; i++){
            s = "string"+i;
            map.put(s, s);
        }
        new Thread("reading"){
            @Override
            public void run() {
                try {
                    for (int i=0; i<10; i++){
                        for (Entry<String, String>ss:map.entrySet()){
                            raf.writeBytes(ss.getKey() + " " + ss.getValue()+"\n");
                            //System.out.println(ss.getKey() + " " + ss.getValue());
                        }
                        //System.out.println("done: " + i);
                        raf.writeBytes("done: " + i + "\n");
                    }
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        
        new Thread("update"){
            @Override
            public void run() {
                Map<String, String> tmp1 = new HashMap<String, String>();
                String ss;
                for (int i=40000; i<50000; i++){
                    ss = "xxx"+i;
                    tmp1.put(ss, ss);
                }
                
                map = tmp1;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                Map<String, String> tmp2 = new HashMap<String, String>();
                for (int i=70000; i<75000; i++){
                    ss = "zzzz"+i;
                    tmp2.put(ss, ss);
                }
                
                map = tmp2;
            }
        }.start();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
