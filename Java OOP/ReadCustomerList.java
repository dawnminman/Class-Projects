//Dawn Inman
import java.nio.file.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;
import java.util.Scanner;
import java.text.*;

public class ReadCustomerList
{
   public static void main(String[] args)
   {
      Path file = Paths.get("C:\\javautica\\customerList.txt");
  
      String[] array = new String[4];
      String s = "";
      String delimiter = ",";
      final String NO_INPUT = "000";

    
    try
    {
      InputStream input = new BufferedInputStream(Files.newInputStream(file));
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));
      s = reader.readLine();
      System.out.println("\nAll Current Customer Balance Records: \n");
      
      
      while(s != null)
      {
         array = s.split(delimiter);
         for(int x = 0; x < array.length; ++x)
            System.out.print
            ( array[x] + " ");
         System.out.println();
         s = reader.readLine();

      
      }
    reader.close();
    }
    catch(Exception e)
    {
      System.out.println("Total Balance Message; " + e);
    }
    
      
   }

}