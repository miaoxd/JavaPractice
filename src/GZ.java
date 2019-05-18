import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.Random;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
public class GZ implements Serializable {
    private boolean makeDirectories(final String path){
        boolean result;
        try{
            (new File(path).getParentFile()).mkdirs();
            result=true;
        }catch(Exception e){
            result=false;
            e.printStackTrace();
        }
        return result;
    }
    private String getParent(final String path){
        StringBuilder result = new StringBuilder();
        try{
            result.append(new File(path).getParent());
            if(!result.toString().endsWith("/")){result.append("/");}
        }catch(Exception e){ e.printStackTrace(); }
        return result.toString();
    }
    public StringBuilder readTextFile(final String filename){
        StringBuilder sb = new StringBuilder();
        FileInputStream fin = null;
        GZIPInputStream gzis = null;
        InputStreamReader xover = null;
        BufferedReader is = null;
        FileChannel channel = null;
        try{
            File file = new File(filename);
            if(file.exists()){
                channel = new RandomAccessFile(file, "rw").getChannel();
                FileLock lock = null;
                int attempt = 0;
                while (lock==null && attempt < 5){
                    if(attempt > 0 ){Thread.sleep(1000);}
                    try{lock=channel.tryLock();}catch(OverlappingFileLockException ofe){}
                    attempt++;
                }
                if(lock !=null){
                    fin = new FileInputStream(file);
                    gzis = new GZIPInputStream(fin);
                    xover = new InputStreamReader(gzis);
                    is = new BufferedReader(xover);
                    String line;
                    while ((line = is.readLine( )) != null)sb.append(line).append("\n");
                    lock.release();
                }
            }
        }catch(EOFException e){
            System.out.println(new StringBuilder("EOF: (probably minor) ").append(filename).toString());
            e.printStackTrace();
        }catch(Exception e){
            System.out.println(new StringBuilder("Bug GZ.readTextFile(): ").append(filename).toString());
            e.printStackTrace();
        }finally{
            if(channel!=null){try{channel.close();}catch(Exception e){}}
            if(is!=null){try{is.close();}catch(Exception e){}}
            if(xover!=null){try{xover.close();}catch(Exception e){}}
            if(gzis!=null){try{gzis.close();}catch(Exception e){}}
            if(fin!=null){try{fin.close();}catch(Exception e){}}
        }
        return sb;
    }
    public boolean writeTextFile(final String filename,final StringBuilder content){
        boolean result=false;
        FileChannel channel=null;
        GZIPOutputStream out=null;
        makeDirectories(filename);
        try {channel=new RandomAccessFile(filename,"rw").getChannel();
            FileLock lock = null;
            int attempt = 0;
            while (lock==null && attempt < 5){
                if(attempt > 0 ){Thread.sleep(1000);}
                try{lock=channel.tryLock();}catch(OverlappingFileLockException ofe){}
                attempt++;
            }
            if(lock !=null){
                out=new GZIPOutputStream(new FileOutputStream(filename));
                out.write(content.toString().getBytes());
                lock.release();
                result=true;
            }
        }catch(Exception e){
            System.out.println(new StringBuilder("Bug GZ.writeTextFile(): ").append(filename).toString());
            e.printStackTrace();
        }finally{
            try{if(channel!=null){channel.close();}}catch(Exception e){}
            try{if(out!=null){out.close();}}catch(Exception e){}
        }
        return result;
    }
    public boolean appendTextfile(final String filename,final StringBuilder content){
        boolean result=false;
        FileInputStream readIn = null;
        GZIPInputStream readGZ = null;
        InputStreamReader readISR = null;
        BufferedReader readBR = null;
        FileChannel readChannel = null;
        FileChannel tempChannel=null;
        GZIPOutputStream tempGZ=null;
        Random r = new Random();
        FileLock readLock=null;
        FileLock tempLock=null;
        // USE TEMP FILE
        makeDirectories(filename);
        String tempname = new StringBuilder(getParent(filename)).append("temp_").append(r.nextInt(9999999)).toString();
        File readFile=new File(filename);
        File tempFile=new File(tempname);
        try{
            tempChannel=new RandomAccessFile(tempFile,"rw").getChannel();
            int attempt=0;
            while(tempLock==null&&attempt<5){if(attempt>0){Thread.sleep(1000);}try{tempLock=tempChannel.tryLock();}catch(OverlappingFileLockException ofe){attempt++;}}
            if(tempLock!=null){tempGZ=new GZIPOutputStream(new FileOutputStream(tempFile));}
            // COPY PREVIOUS RECORDS TO TEMP FILE
            if(readFile.exists()){
                readChannel=new RandomAccessFile(readFile, "rw").getChannel();
                int attemptA=0;
                while(readLock==null&&attemptA<5){
                    if(attemptA>0){Thread.sleep(1000);}
                    try{readLock=readChannel.tryLock();}catch(OverlappingFileLockException ofe){}
                    attemptA++;
                }
                if(readLock!=null){
                    readIn=new FileInputStream(readFile);
                    readGZ=new GZIPInputStream(readIn);
                    readISR=new InputStreamReader(readGZ);
                    readBR=new BufferedReader(readISR);
                    String line;
                    while((line=readBR.readLine())!=null){if(tempLock!=null)tempGZ.write(new StringBuilder(line.trim()).append("\n").toString().getBytes());}
                }
            }
            // WRITE NEW DATA TO TEMPFILE
            if(tempLock!=null){
                if(content.charAt(content.length()-1)!='\n'){content.append("\n");}
                tempGZ.write(content.toString().getBytes());
                tempGZ.finish();
                tempGZ.close();
            }
            // RENAME THE TEMP FILE TO THE OLDFILE
            boolean success = tempFile.renameTo(readFile);
            if(success){tempFile.delete();result=true;}else{System.out.println("HUGE PROBLEM... could not rename file:" + readFile.getAbsolutePath());result=false;}
            // RELEASE THE FILE LOCKS
            if(tempLock!=null){tempLock.release();}
            if(readLock!=null){readLock.release();}
        }catch(EOFException e){System.out.println(new StringBuilder("EOF: (probably minor) ").append(filename).toString());e.printStackTrace();
        }catch(Exception e){System.out.println(new StringBuilder("Bug GZ.appendTextFile(): ").append(filename).toString());e.printStackTrace();
        }finally{
            if(readChannel!=null){try{readChannel.close();}catch(Exception e){}}
            if(readIn!=null){try{readIn.close();}catch(Exception e){}}
            if(readISR!=null){try{readISR.close();}catch(Exception e){}}
            if(readGZ!=null){try{readGZ.close();}catch(Exception e){}}
            if(readBR!=null){try{readBR.close();}catch(Exception e){}}
            if(tempChannel!=null){try{tempChannel.close();}catch(Exception e){}}
            if(tempGZ!=null){try{tempGZ.close();}catch(Exception e){}}
        }
        return result;
    }
    public void renameFile(final String old_name,final String new_name){
        File f = new File(old_name);
        f.renameTo(new File(new_name));
    }
    public boolean prependTextFile(final String filename,final StringBuilder content){
        boolean result=false;
        StringBuilder previous = null;
        try{ previous = readTextFile(filename);}catch(Exception e){/* Did not previously exist */}
        FileChannel channel=null;
        GZIPOutputStream out=null;
        makeDirectories(filename);
        try {channel=new RandomAccessFile(filename,"rw").getChannel();
            FileLock lock = null;
            int attempt = 0;
            while (lock==null && attempt < 5){
                if(attempt > 0 ){Thread.sleep(1000);}
                try{lock=channel.tryLock();}catch(OverlappingFileLockException ofe){}
                attempt++;
            }
            if(lock !=null){
                out=new GZIPOutputStream(new FileOutputStream(filename));
                out.write(content.toString().getBytes());
                if(previous!=null){out.write(previous.toString().getBytes());}
                lock.release();
                result=true;
            }
        }catch(Exception e){
            System.out.println(new StringBuilder("Bug GZ.prependTextFile(): ").append(filename).toString());
            e.printStackTrace();
        }
        finally{
            try{if(channel!=null){channel.close();}}catch(Exception e){}
            try{if(out!=null){out.close();}}catch(Exception e){}
        }
        return result;
    }
}