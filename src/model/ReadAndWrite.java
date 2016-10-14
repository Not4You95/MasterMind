
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.lang.RuntimeException;
/**
 *
 * @author jonas
 */
public class ReadAndWrite {

    private File MyFile;
    /**
     *
     * constructor
     */
    public ReadAndWrite() {
        MyFile = new File("test.txt");
    }

    /**
     *
     * 
     */
    public boolean writeToFile(Player players,File file) throws IOException,AlertToUser {
        System.out.println("write");
        boolean sucses = false;
        ObjectOutputStream output = null;
        MyFile = file;

        try {
            output = new ObjectOutputStream(new FileOutputStream(MyFile, false));
            output.writeObject(players);
            System.out.println(players.getUserName());
            System.out.println("Serializing successfully");
            sucses = true;
            
            output.close();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                throw new AlertToUser("Can not save data!");

            }
        }

        return sucses;

    }

    /**
     * method reads saved information from text file and creates an arraylist
     * with books.
     *
     * @return true if saved, else false
     * @throws ClassNotFoundException
     */
    public Player readFromFile(File file) throws ClassNotFoundException, IOException,AlertToUser {
        Player players = new Player();
        ObjectInputStream input = null;
        MyFile = file;
       
        try {
            input = new ObjectInputStream(new FileInputStream(MyFile));
            players = (Player) input.readObject();
            System.out.println("Deserializing successfully");
            
        } catch (ClassNotFoundException e) {
           throw new AlertToUser("Can not open file!");
        }
        finally{
            try {
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
                
            }
        }

        
        return players;

    }
}
