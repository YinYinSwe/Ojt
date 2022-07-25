package com.inputoutputstream.com;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <h2> IOStreamExample Class</h2>
 * <p>
 * Process for Displaying IOStreamExample
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
public class IOStreamExample {

   
    public static void main(String[] args) throws IOException {
        File file=new File("sample.txt");
        if(!file.exists()) {
            file.createNewFile();
        }
       FileOutputStream fos=new FileOutputStream(file);
       String text="This is a fileinput and output example";
       fos.write(text.getBytes());
       fos.flush();
       fos.close();
       
       FileInputStream fin=new FileInputStream(file);
       int i=fin.read();
       while(!(i==-1)) {
           char c=(char)i;
           System.out.print(c);
           i=fin.read();
       }
       fin.close();

    }

}
