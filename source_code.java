import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class HealthCareApplication {
    private Map<String, String> users = new HashMap<>();
    private Map<Integer, String> healthRecords = new HashMap<>();
    private Map<Integer, Date> appointments = new HashMap<>();
    private Map<String, String> vaccineInfo = new HashMap<>();

    // Constructor to initialize some dummy data
    public HealthCareApplication() {
        // Initialize with some dummy data
        users.put("parentUsername", "parentPassword");
        vaccineInfo.put("Measles", "Measles is a highly contagious viral disease. Vaccination is the best protection.");
    }

    // Method to authenticate user
    public boolean authenticateUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    // Method to register a new user
    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return false;
        }
        users.put(username, password);
        System.out.println("Registration successful! You can now log in.");
        return true;
    }

    // Method to add or update health records
    public void updateHealthRecord(int childId, String recordDetails) {
        healthRecords.put(childId, recordDetails);
        System.out.println("Health record updated for child ID: " + childId);
    }

    // Method to schedule vaccination appointment
    public void scheduleAppointment(int childId, Date appointmentDate) {
        appointments.put(childId, appointmentDate);
        System.out.println("Appointment scheduled for child ID: " + childId + " on " + new SimpleDateFormat("yyyy-MM-dd").format(appointmentDate));
    }

    // Method to retrieve information about diseases and vaccines
    public String getVaccineInformation(String diseaseName) {
        return vaccineInfo.getOrDefault(diseaseName, "No information available for " + diseaseName);
    }
    
    // Additional methods for notifications, reporting, etc.
}

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static HealthCareApplication app = new HealthCareApplication();

    public static void main(String[] args) {
        System.out.println("Welcome to the HealthCare Application!\n");

        boolean exit = false;
        while (!exit) {
            System.out.println("======================================");
            System.out.println("Main Menu:");
            System.out.println("1. Log In");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    if (authenticate()) {
                        handleLoggedInUser();
                    } else {
                        System.out.println("Authentication failed. Please try again.");
                    }
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to handle user authentication
    private static boolean authenticate() {
        System.out.println("======================================");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        boolean isAuthenticated = app.authenticateUser(username, password);
        if (isAuthenticated) {
            System.out.println("Authentication successful!\n");
        } else {
            System.out.println("Invalid username or password.\n");
        }
        return isAuthenticated;
    }

    // Method to handle user registration
    private static void registerUser() {
        System.out.println("======================================");
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        app.registerUser(username, password);
    }

    // Method to display the logged-in user's menu
    private static void handleLoggedInUser() {
        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    updateHealthRecord();
                    break;
                case 2:
                    scheduleAppointment();
                    break;
                case 3:
                    getVaccineInformation();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Logging out. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to display the main menu for logged-in users
    private static void showMenu() {
        System.out.println("======================================");
        System.out.println("User Menu:");
        System.out.println("1. Update Health Record");
        System.out.println("2. Schedule Vaccination Appointment");
        System.out.println("3. Get Vaccine Information");
        System.out.println("4. Log Out");
        System.out.print("Enter your choice: ");
    }

    // Method to update health records
    private static void updateHealthRecord() {
        System.out.println("======================================");
        System.out.print("Enter child ID: ");
        int childId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter health record details: ");
        String recordDetails = scanner.nextLine();
        app.updateHealthRecord(childId, recordDetails);
    }

    // Method to schedule a vaccination appointment
    private static void scheduleAppointment() {
        System.out.println("======================================");
        System.out.print("Enter child ID: ");
        int childId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter appointment date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        try {
            Date appointmentDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            app.scheduleAppointment(childId, appointmentDate);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
        }
    }

    // Method to get vaccine information
    private static void getVaccineInformation() {
        System.out.println("======================================");
        System.out.print("Enter disease name: ");
        String diseaseName = scanner.nextLine();
        String info = app.getVaccineInformation(diseaseName);
        System.out.println("\nVaccine Information:");
        System.out.println(info);
    }
}
