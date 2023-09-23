package Port;

import Resource.ReadDatabase;
import Resource.TableInterface;
import Resource.UserInput;
import Resource.WriteFile;


import javax.sound.sampled.Port;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AdminPort {
    // Attributes
    private String ID;
    private String Name;
    private String Capacity;
    private String landingAbility;
    private String Latitude;
    private String Longitude;

    public AdminPort(String ID, String Name, String Capacity, String landingAbility, String Latitude, String Longitude) {
        this.ID = ID;
        this.Name = Name;
        this.Capacity = Capacity;
        this.landingAbility = landingAbility;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    public AdminPort() {
    }

    public void getAllPortInfo() throws FileNotFoundException {
        ArrayList<String[]> user = new ArrayList<>();
        Scanner filePorts = new Scanner(new File("./src/database/ports.txt"));

        // while loop get info of each port in the scanner.
        while (filePorts.hasNext()) {
            String[] portData = new String[3];
            String line = filePorts.nextLine();
            StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
            String ID = stringTokenizer.nextToken();
            String Name = stringTokenizer.nextToken();
            String Capacity = stringTokenizer.nextToken();
            String landingAbility = stringTokenizer.nextToken();
            String Latitude = stringTokenizer.nextToken();
            String Longitude = stringTokenizer.nextToken();
            portData = new String[]{ID, Name, Capacity, landingAbility, Latitude, Longitude};
            user.add(portData);

        }
        // Print out the table contain all the product information.
        TableInterface.setShowVerticalLines(true);
        TableInterface.setHeaders("ID", "Name", "Capacity", "landing Ability", "Latitude", "Longitude");

        for (int i = 1; i < user.size(); i++) {
            TableInterface.addRow(user.get(i)[0], user.get(i)[1], user.get(i)[2], user.get(i)[3], user.get(i)[4], user.get(i)[5]);
        }

        TableInterface.print();
        TableInterface.setHeaders(new String[0]);
        TableInterface.setRows(new ArrayList<String[]>());
    }

    public void getPortId() throws FileNotFoundException { //Display ports information with options to choose
        ArrayList<String[]> user = new ArrayList<>();
        Scanner filePorts = new Scanner(new File("./src/database/ports.txt"));

        // Use tokenizer to pick data
        while (filePorts.hasNext()) {
            String[] portData = new String[3];
            String line = filePorts.nextLine();
            StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
            String ID = stringTokenizer.nextToken();
            String Name = stringTokenizer.nextToken();
            String Capacity = stringTokenizer.nextToken();
            String landingAbility = stringTokenizer.nextToken();
            String Latitude = stringTokenizer.nextToken();
            String Longitude = stringTokenizer.nextToken();
            portData = new String[]{ID, Name, Capacity, landingAbility, Latitude, Longitude};
            user.add(portData);

        }
        // Print out the table contain all the product information.
        TableInterface.setShowVerticalLines(true);
        TableInterface.setHeaders("ID", "Name", "Capacity", "landing Ability", "Latitude", "Longitude");

        for (int i = 1; i < user.size(); i++) {
            TableInterface.addRow(user.get(i)[0], user.get(i)[1], user.get(i)[2], user.get(i)[3], user.get(i)[4], user.get(i)[5]);
        }

        TableInterface.print();
        TableInterface.setHeaders(new String[0]);
        TableInterface.setRows(new ArrayList<String[]>());
    }

    public void updatePortName(String filepath, String newData, String ID) throws IOException {

        ArrayList<String[]> database = ReadDatabase.readAllLines("./src/database/ports.txt");

        for (String[] strings : database) {
            if (strings[0].equals(ID)) { /* System check ID */
                strings[1] = newData; // Modify the port's name
            }
        }
        File file = new File(filepath);
        PrintWriter printWriter = new PrintWriter(file);

        printWriter.write(""); // erase data
        printWriter.close();

        for (String[] strings : database) {
            WriteFile.rewriteFile(filepath, "ID, Name, Capacity, Landing Ability, Latitude, Longitude", String.join(",", strings)); // Rewrite new data
        }
    }

    public void updatePortCapacity(String filepath, String newData, String ID) throws IOException {

        ArrayList<String[]> database = ReadDatabase.readAllLines("./src/database/ports.txt");

        for (String[] strings : database) {
            if (strings[0].equals(ID)) { // System check ID
                strings[2] = newData; // Modify the port's capacity
            }
        }
        File file = new File(filepath);
        PrintWriter printWriter = new PrintWriter(file);

        printWriter.write(""); // erase data
        printWriter.close();

        for (String[] strings : database) {
            WriteFile.rewriteFile(filepath, "ID, Name, Capacity, Landing Ability, Latitude, Longitude", String.join(",", strings)); // Rewrite new data
        }
    }

    public void updatePortLandingAbility(String filepath, String newData, String ID) throws IOException {

        ArrayList<String[]> database = ReadDatabase.readAllLines("./src/database/ports.txt");

        for (String[] strings : database) {
            if (strings[0].equals(ID)) { /* System check ID */
                strings[3] = newData; // Modify the port's landing ability
            }
        }
        File file = new File(filepath);
        PrintWriter printWriter = new PrintWriter(file);

        printWriter.write(""); // erase data
        printWriter.close();

        for (String[] strings : database) {
            WriteFile.rewriteFile(filepath, "ID, Name, Capacity, Landing Ability, Latitude, Longitude", String.join(",", strings)); // Rewrite new data
        }
    }
}