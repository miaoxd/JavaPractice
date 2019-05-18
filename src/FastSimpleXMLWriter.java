

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * This class output the xml based on NIO.
 * 
 * @since 2013
 *
 */
public class FastSimpleXMLWriter {

    private String filetype = null;

    final static private String ENCODING = "UTF-8";

    final Charset charset = Charset.forName(ENCODING);

    final FileChannel channel;

    //final static private int INDENT = 4;
    final static private String INDENT_SPACE = "    ";

    private int indentNum = 0;

    ZipOutputStream zipOut = null;

    GZIPOutputStream gzOut = null;

    OutputStream flatOut = null;

    final static private String declaration = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    final static private String rootTag = "bulkPmMrDataFile";
    
    //This variable is shared to decrease memory and save time on new instance
    final private StringBuilder sbBuff = new StringBuilder();

    public static void main(String[] args) {
        FastSimpleXMLWriter.test1();
        FastSimpleXMLWriter.test2();
    }
    
    //Test Section
    public static void test1(){
        FastSimpleXMLWriter writer = null;
        try {
            writer = new FastSimpleXMLWriter("test1.xml");
            writer.increaseIndent();
            writer.writeOpenedStartTag("fileHeader");
            writer.writeAttribute("fileFormatVersion", "version");
            writer.writeAttribute("reportTime", "2013");
            writer.writeAttribute("startTime", "201201222200");
            writer.writeAttribute("endTime", "201201222215");
            writer.writeAttribute("period", "15");
            writer.writeAttribute("jobid", "中文测试");
            writer.decreaseIndent();
            writer.writeCloseEndTag();
        } catch (FastSimpleXMLWriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public static void test2(){
        FastSimpleXMLWriter writer = null;
        try {
            writer = new FastSimpleXMLWriter("test2.xml");
            writer.increaseIndent();
            writer.writeOpenedStartTag("fileHeader");
            writer.writeAttribute("fileFormatVersion", "version");
            writer.writeAttribute("reportTime", "2013");
            writer.writeAttribute("startTime", "201201222200");
            writer.writeAttribute("endTime", "201201222215");
            writer.writeAttribute("period", "15");
            writer.writeAttribute("jobid", "中文测试");
            writer.writeCloseEndTag();
            
            writer.writeClosedStartTag("Class");
            writer.writeTextContent("Class contents");
            writer.writeEndTagInSameLine("Class");
            writer.decreaseIndent();
        } catch (FastSimpleXMLWriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    //
    

    public FastSimpleXMLWriter(final String filePathAndName) throws FastSimpleXMLWriterException, IOException {
        initialize(filePathAndName);
        channel = new FileOutputStream(filePathAndName).getChannel();
        writeXMLDeclaration();
        writeClosedStartTag(rootTag);
    }

    public int increaseIndent() {
        return increaseIndent(1);
    }

    public int increaseIndent(final int n) {
        indentNum += n;
        return indentNum;
    }

    public int decreaseIndent() {
        return decreaseIndent(1);
    }

    public int decreaseIndent(final int n) {
        if (indentNum >= n) {
            indentNum -= n;
        } else {
            indentNum = 0;
        }
        return indentNum;
    }

    public int getIndentNum() {
        return indentNum;
    }

    public void writeXMLDeclaration() throws IOException {
        fastwrite(declaration);
    }

    public void writeRootTag() throws IOException {
        writeClosedStartTag(rootTag);
    }

    public void writeOpenedStartTag(final String startTag) throws IOException {
        //sbBuff.delete(0, sbBuff.length());
        sbBuff.append("\n");
        for (int i = 0; i < indentNum; i++) {
            sbBuff.append(INDENT_SPACE);
        }
        sbBuff.append("<" + startTag);
        fastwrite(sbBuff.toString());
        sbBuff.delete(0, sbBuff.length());
    }

    public void writeClosedStartTag(final String startTag) throws IOException {
        writeOpenedStartTag(startTag);
        fastwrite(">");
        //increaseIndent();
    }

    public void writeAttribute(final String attName, final String attValue) throws IOException {
        fastwrite(" " + attName + "=\"" + attValue + "\"");
    }

    public void writeCloseEndTag() throws IOException {
        fastwrite(" />");
        //decreaseIndent();
    }

    public void writeEndTagInNewLine(final String endTag) throws IOException {
        sbBuff.append("\n");
        for (int i = 0; i < indentNum; i++) {
            sbBuff.append(INDENT_SPACE);
        }
        sbBuff.append("</" + endTag + ">");
        fastwrite(sbBuff.toString());
        sbBuff.delete(0, sbBuff.length());
        //decreaseIndent();
    }
    public void writeEndTagInSameLine(final String endTag) throws IOException {
        fastwrite("</" + endTag + ">");
        //decreaseIndent();
    }

    public void fastwrite(final String str) throws IOException {
        channel.write(charset.encode(str));
    }

    public void writeTextContent(final String content) throws IOException {
        fastwrite(content);
    }

    public void write(final String str) throws IOException {
        fastwrite(str);
    }

    public void close() throws IOException {
        if (channel == null || !channel.isOpen()) {
            return;
        }
        writeEndTagInNewLine(rootTag);
        channel.close();
    }

    private void initialize(final String filePathAndName) throws IOException, FastSimpleXMLWriterException {
        filetype = filePathAndName.substring(filePathAndName.lastIndexOf('.') + 1);

        if (filetype == null || filetype.isEmpty() || filetype.equals(filePathAndName)) {
            final String err = "Can not get the extension name. The file name should be ended with .zip, .gz or .xml.";
            throw new FastSimpleXMLWriterException(err);
        }

        mkdir(filePathAndName);

        if (!filetype.equalsIgnoreCase("xml")) {
            final String err = "The extension name is not supported. The file name should be ended with .xml.";
            throw new FastSimpleXMLWriterException(err);
        }
    }

    public static void mkdir(final String filePathAndName) throws FastSimpleXMLWriterException {
        final File file = new File(filePathAndName);
        if (file.getParentFile() == null) {
            return;
        }
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                final String err = "Failed to create the parent path. " + file.getName();
                throw new FastSimpleXMLWriterException(err);
            }
        }
    }

}
