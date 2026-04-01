package ERyder;
import java.time.LocalDateTime;
import java.util.*;

public class AdminPanel {
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;
    private UserRegistration userRegistration;
    private ActiveRental activeRental;
    private LinkedList<ActiveRental> activeRentalsList;
    private Bike bike;
    private ArrayList<RegisteredUsers> registeredUsersList = new ArrayList<>();

    public void simulateApplicationInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter true or false to demonstrate the Registered User: ");
        isRegisteredUser = scanner.nextBoolean();
        scanner.nextLine();
        System.out.println("Please enter the email address: ");
        emailAddress = scanner.nextLine();
        System.out.println("Please enter the location: ");
        location = scanner.nextLine();
        System.out.println("Simulating the analysis of the rental request.");
        bikeID = analyseRequest(isRegisteredUser, emailAddress, location);
        if(locationValid){
            return;
        }
        System.out.println("Simulating e-bike reservation…");
        reserveBike(bikeID);
        System.out.println("Displaying the active rentals…");
        viewActiveRentals();
        System.out.println("Simulating the end of the trip…");
        removeTrip(bikeID);
        System.out.println("Displaying the active rentals after trip end…");
        viewActiveRentals();
    }

    public void userManagementOptions() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to E-Ryder Administrator Panel.");
        System.out.println("What do you want to do?");
        System.out.println("\t1. Add New Users");
        System.out.println("\t2. View Registered Users");
        System.out.println("\t3. Remove Registered Users");
        System.out.println("\t4. Update Registered Users");
        System.out.println("\t6. Demo the Bike Rental System");
        System.out.println("\t5. EXIT");
        
        //BikeRental bikeRental = new BikeRental();
        int choice = scanner.nextInt();
        scanner.nextLine();
        UserService userService = new UserService();
        switch (choice) {
            case 1:
                userService.addNewUsers();
                break;
            case 2:
                RentalService rentalService = new RentalService();
                rentalService.viewActiveRentals();
                break;
            case 3:
                RentalService rentalService2 = new RentalService();
                rentalService2.endRental("B001");
                break;
            case 4:
                userService.updateRegisteredUsers();
                break;
            case 5:
                System.out.println("Exiting...");
                break;
            case 6:
                simulateApplicationInput();
            default:
                System.out.println("Invalid choice. Please try again");
        }

        scanner.close();
    }

    
}