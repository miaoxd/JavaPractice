import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @since 2013
 *
 */
public class FileOperation {

    
    public static void main(String[] args){
        final String fn = 
                "C:\\Users\\exinmia\\Workspaces\\E4E\\CorbaNotification\\src\\test\\resources\\events\\event.xml";
        FileOperation fo = new FileOperation();
        File ff = new File(fn);
        System.out.println(ff.canRead());
        System.out.println(fo.delFile(fn));
        
        try {
            fo.chMTime();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean delFile(final String fn){
        File f = new File(fn);
        return f.delete();
    }
    
    public void chMTime() throws IOException{
        final String fn = "abc.txt";
        final File f = new File(fn);
        RandomAccessFile raf = new RandomAccessFile(f, "rw");
        raf.writeBytes("hell");
        raf.close();
        f.setLastModified(System.currentTimeMillis()-1000*60*60*24);
    }
}
