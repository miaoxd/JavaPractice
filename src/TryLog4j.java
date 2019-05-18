
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author exinmia
 * @since 2015
 *
 */
public class TryLog4j {

    private static final Logger log4j = Logger.getLogger(TryLog4j.class);
    
    private static final String logConfFileName = "conf/log4j.try.xml";
    
    public TryLog4j(){
        DOMConfigurator.configure(logConfFileName);
    }
    
    /**
     * @param args
     * @throws IOException 
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        
        final TryLog4j t = new TryLog4j();
        t.writeLog();
        
        t.changeLogConfFile("info");
        
        Thread.sleep(1000*61);
        
        t.writeLog();
        
        t.changeLogConfFile("debug");
    }
    
    public void writeLog(){
        
        log4j.debug("debug");
        log4j.info("info");
        log4j.error("error");
        log4j.warn("warn");
        log4j.fatal("fatal");
        
    }
    
    public void changeLogConfFile(final String to) throws IOException{
        final File dest = new File(logConfFileName);
        if (!dest.exists()){
            dest.createNewFile();
        }
        FileChannel destCh = new FileOutputStream(dest).getChannel();
        
        final File sou = new File(logConfFileName+"."+to);
        if (!sou.exists()){
            throw new IOException("source file not exists");
        }
        FileChannel souCh = new FileInputStream(sou).getChannel();
        destCh.transferFrom(souCh, 0, souCh.size());
        
        souCh.close();
        destCh.close();
        
    }

}
