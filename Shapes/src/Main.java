/**
 *
 * //Project : Shapes (PSE 1st Assignment)
 * //Date : 6/10/2019
 * //Author : Sadi Mamedov LHXOTB
 *
 **/

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class Main {


    public static void main (String [] args) {

        Database database = new Database();
        try {
            database.read("text_files/data.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        //database.report();
        System.out.println("There are " + database.countPointHolder() + " shapes which contain the given point");


    }
}
