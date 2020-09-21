//Dawn Inman
import java.nio.file.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;
import java.util.Scanner;
import java.text.*;

public class DisplaySelectedCustomer
{
   public static void main(String[] args)
   {
      try
      {
         Scanner kb = new Scanner(System.in);
         Path file = Paths.get("C:\\javautica\\customerList.txt");
         final String ID_NUMBER_FORMAT = "00000";
         final String FIRST_NAME_FORMAT = "       ";
         final int FIRST_NAME_LENGTH = FIRST_NAME_FORMAT.length();
         final String LAST_NAME_FORMAT = "        ";
         final int LAST_NAME_LENGTH = LAST_NAME_FORMAT.length();
         final String BALANCE_OWED_FORMAT = "";
         String delimiter = ",";
         String s = ID_NUMBER_FORMAT + delimiter + FIRST_NAME_FORMAT + delimiter + LAST_NAME_FORMAT + delimiter + BALANCE_OWED_FORMAT + System.getProperty("line.separator");
         final int RECSIZE = s.length();
         
         byte data[] = s.getBytes();
         final String EMPTY_ACCT = "000";
         String[] array = new String [4];
         
         InputStream iStream = new BufferedInputStream(Files.newInputStream(file));
         BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
         
         FileChannel fc = (FileChannel)Files.newByteChannel(file, READ);
         ByteBuffer buffer = ByteBuffer.wrap(data);
         int findAcct;
         System.out.print("\nEnter account number to retrieve >>> ");
         findAcct = kb.nextInt();
         
         fc.position(findAcct * RECSIZE);
         fc.read(buffer);
         s = new String(data);
         array = s.split(delimiter);
         if(array[1] != null && array[1].trim().length() != 0)
            System.out.println("Requested Record: " + s);
         else
            System.out.println("Record does not exist. Invalid customer number.");
      
      }
      catch(Exception e)
      {
          System.out.println("Message: " + e);
      }
   }

}