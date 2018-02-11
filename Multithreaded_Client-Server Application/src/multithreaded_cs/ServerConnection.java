/*
Author - Aniket Gade
UTA ID - 1001505046
NOTE: 1) The code for Multithreaded Server is based on the original code by Gliderman's Channel. “Java Ep32 - Networking Pt2 - Multithreaded Server.” YouTube, YouTube, 19 May 2016, www.youtube.com/watch?v=RQ2v0CSV4tY.
      2) The code for accessing Excel Sheet is based on the original code by Stl, Murali. “Read Write From Excel Sheet.” YouTube, YouTube, 3 June 2014, www.youtube.com/watch?v=ZGGU4aunris.
*/

/*
This class implements the actual multithreading of the server. The processing of finding synonyms of the input string given by the user is done here.
The socket at which connection is accepted and an instace of the Server class are the parameters are passed here.
Apache POI is used to access the excel sheet "test.xlsx" which contains the alternative words.
 */
package multithreaded_cs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ServerConnection extends Thread{
    static Socket socket; //Initialize a socket
    Server server; // Initialize a server
    BufferedInputStream din; // BufferedInputStream is used to store the data read form the stream. 
    BufferedOutputStream dout; // BufferedOutputStream is used to store the data that if to be written on the stream.
    static StringBuffer input; // Used to store the apended input charachters 
    int character; //used to read a charater off the stream
    static int RowCount = 0; // Indicates the total number of rows in the excel sheet.
    static String ip; //Stores the input word, this string is used to compare it with the database(in this case the data in the excel sheet)
    static String output=""; //Used to retrieve similar meanings from the excel sheet and send it to the client
    
    public ServerConnection(Socket socket, Server server)
    {
        super(); // Call to the super constructor.
        this.socket=socket;
        this.server=server;
    }
    

     public static String search() throws FileNotFoundException, EncryptedDocumentException, InvalidFormatException, IOException
    /*
    This function is used to search the similar meanings for the word recieved from the client.
    It returns the string "output" containing the similar meanings if the word is found in the excel sheet.
    Else it returns and empty string.
    */
    {
            String dir = System.getProperty("user.dir"); //Get the path of current working directory.
            String file = dir+"/test.xlsx"; //add the name of excel sheet to the current working directory path.
            FileInputStream fis = new FileInputStream(file); // Open the file "text.xlsx"
            Workbook wb = WorkbookFactory.create(fis); // Open an Excel workbook of "text.xlsx" using WorkbookFactory class 
            Sheet s = wb.getSheet("Sheet1"); //Open the sheet "Sheet1" in "text.xlsx"
            RowCount = s.getLastRowNum();  //Get the total number of rows in the excel sheet "Sheet1"
            for (int i = 0; i <= RowCount; i++) { //In order to compare the word to all the words in the sheet1 we loop for "total number of rows" times.
                Row row = s.getRow(i);//Get the current row number 
                Cell cell = row.getCell(0);//Get 0th cell (This cell contains the word to which we want to compare)
                String cell_val = cell.getStringCellValue(); //Get the contents of the cell
                String ip = input.toString(); //Convert the contents of this cell to a string to compare.
                if(ip.equals(cell_val)) //Compare the word given by the client to the word in the database
                {
                    cell = row.getCell(1); // If equal, copy all the similar meanings (stored at 1st cell in the same row)
                    output = cell.toString(); //Convert it to a string
                }
            }
        return output; // Return the similar meanings 
    }
    
    public void run()
    /*
    Override the default run method of the thread. Here, data is read off the stream. Then the search() function is called to recieve the string containing similar meanings.
    Then this string is sent back to the client        
    */            
    {
        try {
            din = new BufferedInputStream(socket.getInputStream()); //Create a BufferedInputStream
            dout = new BufferedOutputStream(socket.getOutputStream()); //Create a BufferedOutputStream

            while(true)
            {
                while (din.available() == 0) {   //Loop until data is not availabe at the input stream.                 
                    try {
                        Thread.sleep(1000);//If no data is available, make the thread sleep for 1s.
                        //System.out.println(Thread.activeCount() + " Sleeping");
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                }
                input = new StringBuffer(); //Local string to store the character which is read
                while((character = din.read()) != 13) { //Read until end of stream (number 13) is recieved.
                input.append((char)character); // Append every character.
                }
                display_gui.client_req.setText(input.toString()); // Display the recieved word on the Server GUI.
                try {
                    search(); // Call to the search function, this returns the string output
                } catch (FileNotFoundException | EncryptedDocumentException | InvalidFormatException ex) {
                    System.out.println(ex);
                }
                String returnCode = output+(char) 13;// This string is used to send the string output+13 (Where 13 is end of stream character)
                BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream()); // Init BufferedOutputStream
                OutputStreamWriter osw = new OutputStreamWriter(os, "US-ASCII"); // Init OutputStreamWriter
                osw.write(returnCode); // Write the string returnCode onto the output stream.
                osw.flush(); // Flush the stream.
                output="";// Reset the output string to empty.
                break;
            }
            din.close(); //Close DataInputStream
            dout.close(); //Close DataOutputStream
        }
            
        
        catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
}
