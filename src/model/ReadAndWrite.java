package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author jonas
 */
public class ReadAndWrite {

    private File MyFile;

    /**
     * constructor
     */
    public ReadAndWrite() {
        MyFile = new File("test.txt");
    }

   /**
    *  This method saves the data of the type player in to a file   
    * @param file is the file the data will be saved at
    * @throws AlertToUser error massage to the user
    */
    public void writeToFile(Player players, File file) throws IOException, AlertToUser {
        System.out.println("write");
        boolean success = false;
        ObjectOutputStream output = null;
        MyFile = file;

        try {
            output = new ObjectOutputStream(new FileOutputStream(MyFile, false));
            output.writeObject(players);
            System.out.println(players.getUserName());

            output.close();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                throw new AlertToUser("Can't save data!");

            }
        }

    }

    /**
     * method reads saved information from text file and returns the player objekt
     * @param file is the file the will be open.  
     * @throws AlertToUser returns error massage to the user
     */
    public Player readFromFile(File file) throws ClassNotFoundException, IOException, AlertToUser {
        Player players = new Player();
        ObjectInputStream input = null;
        MyFile = file;

        try {
            input = new ObjectInputStream(new FileInputStream(MyFile));
            players = (Player) input.readObject();

        } catch (ClassNotFoundException e) {
            throw new AlertToUser("Can't open file!");
        } finally {
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
