//Dawn Inman
import java.nio.file.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;
import java.util.Scanner;
import java.text.*;

public class WriteCustomerList
{
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      Path file = Paths.get("C:\\javautica\\customerList.txt");
      String[] arrayOption = new String[3];
      final String ID_NUMBER_FORMAT = "00000";
      final String FIRST_NAME_FORMAT = "       ";
      final int FIRST_NAME_LENGTH = FIRST_NAME_FORMAT.length();
      final String LAST_NAME_FORMAT = "        ";
      final int LAST_NAME_LENGTH = LAST_NAME_FORMAT.length();
      final String BALANCE_OWED_FORMAT = "";
      String delimiter = ",";
      String s = ID_NUMBER_FORMAT + delimiter + FIRST_NAME_FORMAT + delimiter + LAST_NAME_FORMAT + delimiter + BALANCE_OWED_FORMAT + System.getProperty("line.separator");
      final int SIZE = s.length();
      
      FileChannel fcList = null;
      String idNumberString;
      int idNumber;
      String firstName;
      String lastName;
      double balanceOwed;
      final String QUIT = "999";
      
      createEmptyFile(file, s);
      
      try
      {
        fcList = (FileChannel)Files.newByteChannel(file, CREATE, WRITE);
      
        System.out.print("Enter the Customer Number (0-199) >> ");
        idNumberString = input.nextLine();
      
        while(!(idNumberString.equals(QUIT)))
        {
           idNumber = Integer.parseInt(idNumberString);
        
           System.out.print("Enter the Customer's First Name >> ");
           firstName = input.nextLine();
           StringBuilder sb = new StringBuilder(firstName);
           sb.setLength(FIRST_NAME_LENGTH);
           firstName = sb.toString();
           
           System.out.print("Enter the Customer's Last Name >> ");
           lastName = input.nextLine();
           StringBuilder sbr = new StringBuilder(lastName);
           sbr.setLength(LAST_NAME_LENGTH);
           lastName = sbr.toString();
           
           System.out.print("Enter the balance owed >> ");
           balanceOwed = input.nextDouble();
           input.nextLine();
          
           
           s = idNumberString + delimiter + firstName + delimiter + lastName + delimiter + balanceOwed + System.getProperty("line.separator");
           
           byte data[] = s.getBytes();
           ByteBuffer buffer = ByteBuffer.wrap(data);
           
           fcList.position(idNumber * SIZE);
           fcList.write(buffer);
           
           System.out.print("Enter Next Customer Number (0-199) or " + QUIT + " to quit >> ");
           idNumberString = input.nextLine();  
        }
        
        fcList.close();
      }
      catch(Exception e)
       {
         System.out.println("Data Entry Error message: " + e);
       }  
      }
      public static void createEmptyFile(Path file, String s)
      {
         final int NUM_CUST = 50;
         
         try
         {
            OutputStream outputStr = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStr));
            
            for(int id = 0; id < NUM_CUST; ++id)
               writer.write(s, 0, s.length());
            writer.close();
            
         }
         catch(Exception e)
         {
            System.out.println("Create Empty File Error message: " + e);
         }  
       
   }
}