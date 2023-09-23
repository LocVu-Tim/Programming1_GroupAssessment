package Menu;

import Container.AdminContainer;
import Container.PMContainer;
import LinesHandler.FiltersType;
import LinesHandler.LineFilters;
import Port.PMPort;
import Resource.ReadDatabase;
import Resource.UserInput;
import Port.AdminPort;
import Trip.PMTrip;
import Vehicle.AdminVehicle;
import User.Admin;
import Vehicle.PMVehicle;
import interfaces.builders.OptionsInterface;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AdminMenu {

    // Display a menu for user before login
    public void view() throws IOException, ParseException, InterruptedException {
        System.out.println("\n================================================= WELCOME TO TOM'S PRODIGIES STORE =================================================");
        System.out.println("1. Login by admin account");
        System.out.println("2. Back to authentication system");
        System.out.println("3. Exit");

        boolean validUser = false;
        Admin admin = new Admin();
        Authentication authentication = new Authentication();
        String option = UserInput.rawInput();

        switch (option) {

            case "1":
                System.out.println("\n================================================= LOGIN FORM =================================================");
                do {
                    // Ask user to input username and password
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    // if the username and password are not correct, the system will ask user to input again
                    if (!admin.verifyAdmin(username, password)) {
                        System.out.println("Wrong password, try again!");
                    } else {
                        this.viewHomepage();
                        validUser = true;
                    }
                } while (!validUser);

            case "2":
                authentication.mainMenu();

            case "3":
                System.exit(1);

            default:
                // System give user message and back to the viewpage
                System.out.println("THERE IS NO MATCHING RESULT, PLEASE TRY AGAIN!!!");
                TimeUnit.SECONDS.sleep(1);
                this.view();
        }
    }

    // Admin homepage when user log in successfully
    public void viewHomepage() throws IOException, InterruptedException, ParseException {
        System.out.println("\n================================================= HOMEPAGE =================================================");
        System.out.println("1. Ports' information");
        System.out.println("2. Vehicles' information");
        System.out.println("3. Containers information");
        System.out.println("4. Trips Information");
        System.out.println("5. Port managers' information");
        System.out.println("6. Log out");
        System.out.println("7. Exit");

        Scanner scanner = new Scanner(System.in);
        AdminPort Port = new AdminPort();
        Admin User = new Admin();
        AdminVehicle Vehicle = new AdminVehicle();
        AdminMenu adminMenu = new AdminMenu();
        AdminContainer Container = new AdminContainer();
        String choice = UserInput.rawInput();

        switch (choice) {
            case "1":
                // Allow user view and update ports
                System.out.println("\n================================================= PORT'S INFORMATION =================================================");
                System.out.println("1. List all ports' information");
                System.out.println("2. Add port");
                System.out.println("3. Remove port");
                System.out.println("4. Update ports' name");
                System.out.println("5. Update ports' capacity");
                System.out.println("6. Update ports' landing ability");
                System.out.println("7. Back to homepage");
                String portOptions = UserInput.rawInput();

                switch (portOptions) {
                    // Display the information of all Ports
                    case "1":
                        Port.getAllPortInfo();
                        TimeUnit.SECONDS.sleep(1);
                        adminMenu.viewHomepage();

                        //Create new port
                    case "2":
                        User.addPort();
                        TimeUnit.SECONDS.sleep(1);
                        adminMenu.viewHomepage();

                        //Delete port
                    case "3":
                        User.deletePort();
                        TimeUnit.SECONDS.sleep(1);
                        adminMenu.viewHomepage();

                        //Update port's name
                    case "4":
                        Port.getAllPortInfo();
                        System.out.print("Enter port's ID to update: ");
                        String pnId = scanner.nextLine();
                        System.out.println("Update port's name to:");
                        String name = scanner.nextLine();
                        Port.updatePortName("./src/database/ports.txt", name, pnId);
                        adminMenu.viewHomepage();

                        //Update port's capacity
                    case "5":
                        Port.getAllPortInfo();
                        System.out.print("Enter port's ID to update: ");
                        String pcId = scanner.nextLine();
                        System.out.println("Update port's capacity to:");
                        String capacity = scanner.nextLine();
                        Port.updatePortCapacity("./src/database/ports.txt", capacity, pcId);
                        adminMenu.viewHomepage();

                        //Update port's landing ability
                    case "6":
                        Port.getAllPortInfo();
                        System.out.print("Enter port's ID to update: ");
                        String laId = scanner.nextLine();
                        System.out.println("Update port's landing ability to:");
                        String landingAbility = scanner.nextLine();
                        Port.updatePortLandingAbility("./src/database/ports.txt", landingAbility, laId);
                        adminMenu.viewHomepage();

                        //Back to homepage
                    case "7":
                        adminMenu.viewHomepage();
                }

            case "2":
                // Allow user view and update vehicle
                System.out.println("\n================================================= VEHICLE'S INFORMATION =================================================");
                System.out.println("1. List all vehicles' information");
                System.out.println("2. Add vehicle");
                System.out.println("3. Remove vehicle");
                System.out.println("4. Update vehicles' carrying capacity");
                System.out.println("5. Back to homepage");
                String vehicleOptions = UserInput.rawInput();

                switch (vehicleOptions) {
                    // Display the information of all vehicle
                    case "1":
                        Vehicle.getAllVehicleInfo();
                        TimeUnit.SECONDS.sleep(1);
                        adminMenu.viewHomepage();

                        //Create vehicle
                    case "2":
                        User.addVehicle();
                        TimeUnit.SECONDS.sleep(1);
                        adminMenu.viewHomepage();

                        //Delete new vehicle
                    case "3":
                        User.deleteVehicle();
                        TimeUnit.SECONDS.sleep(1);
                        adminMenu.viewHomepage();

                        //Update vehicle's capacity
                    case "4":
                        Vehicle.getAllVehicleInfo();
                        System.out.print("Enter vehicle's ID to update: ");
                        String vcId = scanner.nextLine();
                        System.out.println("Update vehicle's capacity to:");
                        String capacity = scanner.nextLine();
                        Vehicle.updateVehicleCapacity("./src/database/vehicles.txt", capacity, vcId);
                        adminMenu.viewHomepage();

                        //Back to homepage
                    case "5":
                        adminMenu.viewHomepage();
                }

            case "3":
                // Allow user view and update container
                System.out.println("\n================================================= CONTAINER'S INFORMATION =================================================");
                System.out.println("1. List all containers' information");
                System.out.println("2. Add container");
                System.out.println("3. Remove container");
                System.out.println("4. Update containers' weight");
                System.out.println("5. Update containers' type");
                System.out.println("6. Load container");
                System.out.println("7. Unload container");
                System.out.println("8. Back to homepage");
                String containerOptions = UserInput.rawInput();

                switch (containerOptions) {
                    // Display the information of all container
                    case "1":
                        Container.getAllContainerInfo();
                        TimeUnit.SECONDS.sleep(1);
                        adminMenu.viewHomepage();

                    //Create new container
                    case "2":
                        User.addContainer();
                        TimeUnit.SECONDS.sleep(1);
                        adminMenu.viewHomepage();

                        //Delete container
                    case "3":
                        User.deleteContainer();
                        TimeUnit.SECONDS.sleep(1);
                        adminMenu.viewHomepage();

                    //Update container's weight
                    case "4":
                        Container.getAllContainerInfo();
                        System.out.print("Enter container's ID to update: ");
                        String cwId = scanner.nextLine();
                        System.out.println("Update container's weight to:");
                        String weight = scanner.nextLine();
                        Container.updateContainerWeight("./src/database/containers.txt", weight, cwId);
                        adminMenu.viewHomepage();

                    //Update container type
                    case "5":
                        Container.getAllContainerInfo();
                        System.out.print("Enter container's ID to update: ");
                        String ctId = scanner.nextLine();
                        System.out.println("Update container's type to:");
                        String type = scanner.nextLine();
                        Vehicle.updateVehicleType("./src/database/vehicles.txt", type, ctId);
                        adminMenu.viewHomepage();

                    // Load Container
                    case "6":
                        OptionsInterface portsInterface = PMPort.createOptionsInterfaceForPorts("What port you want to update?", null);
                        HashMap<String, String> interfaceData = portsInterface.run(null);

                        if(!interfaceData.get("option").equals("Return")){
                            String portLine = interfaceData.get("data");
                            String[] portParts = portLine.split(",");

                            String portId = portParts[PMPort.colId-1];
                            PMVehicle.loadContainer(portId);
                        }

                    // Unload Container
                    case "7":
                        portsInterface = PMPort.createOptionsInterfaceForPorts("What port you want to update?", null);
                        interfaceData = portsInterface.run(null);

                        if(!interfaceData.get("option").equals("Return")){
                            String portLine = interfaceData.get("data");
                            String[] portParts = portLine.split(",");

                            String portId = portParts[PMPort.colId-1];

                            PMVehicle.unloadContainer(portId);
                        }

                    case "8":
                        adminMenu.viewHomepage();
                }

            case "4":
                // Allow user view and update container
                System.out.println("\n================================================= TRIP'S INFORMATION =================================================");
                System.out.println("1. Display all trips from the port");
                System.out.println("2. Display all trips from database");
                System.out.println("3. Display all trips from a given day");
                System.out.println("4. Display all trips by days range");
                System.out.println("5. Create new trip");
                System.out.println("6. Complete trip");
                System.out.println("7. Back to homepage");
                String tripOptions = UserInput.rawInput();

                switch (tripOptions) {

                    // Display all trips from the port
                    case "1":
                        Container.getAllContainerInfo();
                        TimeUnit.SECONDS.sleep(1);
                        adminMenu.viewHomepage();

                    // Create new trip
                    case "5":
                        OptionsInterface portsInterface = PMPort.createOptionsInterfaceForPorts("What port you want to update?", null);
                        HashMap<String, String> interfaceData = portsInterface.run(null);

                        if(!interfaceData.get("option").equals("Return")){
                            String portLine = interfaceData.get("data");
                            String[] portParts = portLine.split(",");

                            String portId = portParts[PMPort.colId-1];

                            PMTrip.createATrip(portId);
                        }

                    // Complete trip
                    case "6":
                        portsInterface = PMPort.createOptionsInterfaceForPorts("What port you want to update?", null);
                        interfaceData = portsInterface.run(null);

                        if(!interfaceData.get("option").equals("Return")){
                            String portLine = interfaceData.get("data");
                            String[] portParts = portLine.split(",");

                            String portId = portParts[PMPort.colId-1];

                            PMTrip.completeTrip(portId);
                        }

                    case "7":
                        adminMenu.viewHomepage();


                }
        }
    }
}
